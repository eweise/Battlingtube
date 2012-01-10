package com.bt.domain

import com.battlingtube.util.AppUtils
import com.bt.domain.reference.Category
import com.bt.services.EventService
import com.battlingtube.domain.*

class Battle implements Commentable {

  String name
  String description
  int numberOfRounds = 1
  int roundDuration = 1
  int percentageToNextRound = 50
  Date startTime
  Date finishTime
  User creator
  Date createTime = AppUtils.currentDate
  Category category
  List<Comment> comments = new ArrayList<Comment>();
  boolean featured = false
  public static final String NOT_STARTED = "Not Started"
  public static final String STARTED = "Started"
  public static final String FINISHED = "Finished"
  public static final String CANCELLED = "Cancelled"


  String state = NOT_STARTED

  List<Round> rounds = new ArrayList<Round>()
  String emails
  Set<BattleEntry> battleEntries = new HashSet<BattleEntry>()
  EventService eventService

  def sessionFactory

  static transients = ['eventService']

  static hasMany = [rounds: Round, battleEntries: BattleEntry, comments: Comment]

  static constraints = {
    startTime(nullable: true)
    finishTime(nullable: true)
    name(blank: false)
    description(blank: false)
    numberOfRounds(min: 1, max: 5)
    emails(nullable: true)
  }

  public synchronized void cancel() {
    if (!notStarted()) {
      throw new BattleStateException(state, CANCELLED)
    }
    state = CANCELLED
    eventService.sendEvent new BattleEvent(this, BattleEvent.Action.cancelled)


  }

  public synchronized void start() {

    if (battleEntries.size() < 2) {
      throw new InsufficientNumberOfEntriesException(battleEntries.size())
    }
    if (state.equals(NOT_STARTED)) {
      state = STARTED
      startTime = AppUtils.currentDate
      // create the first round
      addRound()

      eventService.sendEvent new BattleEvent(this, BattleEvent.Action.started)
    }
    else {
      throw new BattleStateException(state, STARTED)
    }
  }

  public synchronized void finish() {
    if (state.equals(STARTED)) {
      state = FINISHED
      finishTime = AppUtils.currentDate
      eventService.sendEvent new BattleEvent(this, BattleEvent.Action.finished)
    }
    else {
      throw new BattleStateException(state, FINISHED)
    }
  }



  public void checkState() {
    if (!started()) {
      throw new BattleStateException(state, "Move to next round")
    }

    if (currentRound().pastEndDate()) {
      if (isLastRound()) {
        finish();
      } else {
        addRound()
        eventService.sendEvent new BattleEvent(this, BattleEvent.Action.roundChanged)
        log.info("moved battle id " + id + " to round " + rounds.size());
      }
    }
    log.info("battle checkState finished")
  }



  private boolean isLastRound() {
    return getRounds().size() == this.numberOfRounds;
  }

  public synchronized boolean notStarted() {
    return state == NOT_STARTED
  }

  public synchronized boolean started() {
    return state == STARTED
  }

  public synchronized boolean finished() {
    return state == FINISHED
  }

  public synchronized boolean cancelled() {
    return state == CANCELLED
  }

  public Round currentRound() {
    return rounds.size() == 0 ? null : rounds[rounds.size() - 1]
  }

  public int currentRoundIndex() {
    return rounds.size()
  }


  public static List<Battle> findNotStartedByUser(User user) {
    String query = "from com.bt.domain.Battle b where b.creator = :user and b.state = :state order by b.name desc"
    return Battle.executeQuery(query, [user: user, state: Battle.NOT_STARTED])
  }

  public static List<Battle> findNotStarted() {
    return Battle.executeQuery("from Battle where state = '${Battle.NOT_STARTED}'")
  }

  public static QueryResult findStarted(QuerySpec spec) {
    def resultCount = Battle.executeQuery("select count(b) from Battle b where state = '${Battle.STARTED}'").get(0)
    def results =  Battle.executeQuery("from Battle where state = '${Battle.STARTED}' order by startTime desc", [max:spec.getPageSize(),offset:spec.getfirstResult()])
    return new QueryResult(results, resultCount.intValue(), spec.pageSize, spec.page );
  }


  public static List<Battle> findMostRecentlyStarted(Category category, int limit) {
    return Battle.findAll("from Battle b where state = '${STARTED}' and category = :category order by b.startTime desc", [max: limit, category: category])
  }

  public static List<Battle> findMostRecentlyCreated(Category category, int limit) {
    return Battle.findAll("from Battle b where state = '${NOT_STARTED}' and category = :category order by b.createTime desc", [max: limit, category: category])
  }

  public static List<Battle> findMostRecentlyStarted(int limit) {
    return Battle.findAll("from Battle b where state = '${STARTED}' order by b.startTime desc", [max: limit])
  }

  public static List<Battle> findMostRecentlyCreated(int limit) {
    return Battle.findAll("from Battle b where state = '${NOT_STARTED}' order by b.createTime desc", [max: limit])
  }

  public static List<Battle> findMostRecentlyFinished(int limit) {
    return Battle.findAll("from Battle b where state = '${FINISHED}' order by b.createTime desc", [max: limit])
  }

  public static List<Battle> findByText(String text, int limit) {
    def c = Battle.createCriteria();
    return c {
      or {
        ilike("name", "%${text}%")
        ilike("description", "%${text}%")
        category {
          ilike("name", "%${text}%")
        }
        creator {
          profile {
            ilike("username", "%${text}%")
          }
        }
      }
      maxResults(limit)
    }
  }

  public static List<Battle> findCreated(User creator) {
    String query = "from com.bt.domain.Battle b where b.creator = :creator"
    return Battle.executeQuery(query, [creator: creator])
  }

  public static int countCreated(User creator) {
    String query = "select count(b) from com.bt.domain.Battle b where b.creator = :creator"
    return Battle.executeQuery(query, [creator: creator]).get(0)
  }

  public Ranking findLeader() {
    assert state != NOT_STARTED: "There is no leader.  com.bt.domain.Battle has not started"
    return currentRound().findLeader()
  }

  public static List<Battle> findVoted(User voter, int limit) {
    String query = "select distinct b from com.bt.domain.Battle b join b.rounds r join r.votes v where v.voter = :voter order by b.startTime desc"
    return Battle.executeQuery(query, [voter: voter, max:limit])
  }

  public static int countVoted(User voter) {
    String query = "select count(distinct b) from com.bt.domain.Battle b join b.rounds r join r.votes v where v.voter = :voter"
    return Battle.executeQuery(query, [voter: voter]).get(0)
  }

  public static List<Battle> findReadyToBeStarted(User creator) {
    String query = "select b from Battle b join b.battleEntries be having count(be) > 1 and b.state = '${NOT_STARTED}' and b.creator = :creator"
    return Battle.executeQuery(query, [creater: creator])
  }

  public Round addRound() {
    if (state != STARTED) {
      throw new InvalidBattleStateException("Cannot add round.  com.bt.domain.Battle is not started")
    }
    Round round = new Round(this, roundDuration, percentageToNextRound)

    // If there are fewer than 2 entries, then end the battle
    if (round.battleEntries.size() < 2) {
      finish()
      return currentRound()
    }
    else {
      this.addToRounds(round)

      this.save()
      round.save()
      return round
    }
  }

  public BattleEntry getRandomEntryForUser(User user) {
    return currentRound().getRandomEntryForUser(user)
  }

  static Battle findStartedWithMostVotes() {
    def query = """select b.id, count(v) from Battle b join b.rounds r join r.votes v
                    where b.state = '${Battle.STARTED}'
                    group by b  """
    def results = Battle.executeQuery(query, [max: 1])
    if (results.size() > 0) {
      return Battle.get(results[0][0])
    }
    else {
      return null;
    }
  }


  public static List<Battle> findBattlesByCreatorOrderedByDate(User creator, int limit) {
    def query = """select b from Battle b where b.creator = :creator order by createTime desc"""

    List results = Battle.executeQuery(query, [creator:creator, max:limit])
    return results
  }


  public Commenter commenter() {
    return new Commenter(this);
  }

  
}
