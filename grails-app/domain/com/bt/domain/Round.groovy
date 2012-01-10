package com.bt.domain

import com.battlingtube.util.AppUtils
import java.math.RoundingMode


class Round {

  Battle battle
  int durationDays
  int percentageToAdvance
  Date startDate
  Date endDate
  List<Vote> votes = new ArrayList<Vote>()

  Set<BattleEntry> battleEntries = new HashSet<BattleEntry>()

  static hasMany = [votes: Vote, battleEntries: BattleEntry]

  static belongsTo = Battle

  protected Round() {

  }

  public getRoundIndex() {
    return battle.rounds.indexOf(this) + 1
  }

  public Round(Battle battle, int durationDays, int percentageToAdvance) {
    assert percentageToAdvance > 1: "percentageToAdvance must be greater than 1"

    // Initialize attributes
    this.battle = battle;
    this.durationDays = durationDays
    this.percentageToAdvance = percentageToAdvance

    startDate = AppUtils.currentDate
    Calendar cal = new GregorianCalendar()
    cal.time = startDate
    cal.add Calendar.DAY_OF_YEAR, durationDays
    endDate = cal.time


    if (battle.currentRoundIndex() == 0) {
      copyAllEntriesFromBattle(battle)
    }
    else {
      Round previousRound = previousBattleRound(battle)
      // nothing to add to new round
      if (previousRound.battleEntries.size() == 0) {
        return
      }

      int totalToAdvance = previousRound.battleEntries.size() * (percentageToAdvance * 0.01)

      if (totalToAdvance == 0) {
        return
      }

      List<Ranking> rankings = previousRound.findRankings()

      for (int i in 0..totalToAdvance - 1) {
        this.battleEntries.add(rankings.get(i).battleEntry)
      }
    }
  }

  private def previousBattleRound(Battle battle) {
    battle.rounds[battle.currentRoundIndex() - 1]
  }

  private def copyAllEntriesFromBattle(Battle battle) {
    return battle.battleEntries.each {
      addToBattleEntries(it)
    }
  }

  public void addVote(Vote vote) {
    votes.add(vote);
  }

  public boolean pastEndDate() {
    return AppUtils.currentDate.after(endDate)
  }

  public List<Ranking> findRankings() {

    List<Ranking> rankings = new ArrayList<Ranking>()

    battleEntries.each {BattleEntry battleEntry ->
      rankings.add(findRanking(battleEntry))
    }
    return rankings.sort {  it.score*-1 }
  }

  public Ranking findRanking(BattleEntry battleEntry) {
    String sql = "select sum(v.score),  count(v.score) from com.bt.domain.Round r  join r.votes v join v.battleEntry be where r = :round and be = :battleEntry "
    def results = BattleEntry.executeQuery(sql, [round: this, battleEntry: battleEntry])
    Long sum = results[0][0]
    Long count  = results[0][1]
    
    BigDecimal score = (sum == 0 || count == 0) ? new BigDecimal(0) : new BigDecimal(sum / count)

    score = score.setScale(2, RoundingMode.HALF_UP)
    return new Ranking([battleEntry: battleEntry, score: score])
  }

  public List<Ranking> findRankingsOrderedByScore() {
    String sql = "select com.bt.domain.Ranking, avg(v.score) from com.bt.domain.Round r  join r.votes v join v.battleEntry be where r = :round and be = :battleEntry "
    Round.executeQuery(sql, [round: this, battleEntry: battleEntry])
  }

  public Ranking findLeader() {
    List<Ranking> rankings = findRankings()
    if (rankings.size() == 0) {
      return null
    }
    return rankings[0]
  }

  public BattleEntry getRandomEntryForUser(User user) {
    String query = "select be from com.bt.domain.Round r join r.battleEntries be where r = :round \
    and be not in(select vbe from com.bt.domain.Vote v join v.battleEntry vbe where v.round = :round and v.voter = :user)"
    List results = Battle.executeQuery(query, [user: user, round: this])
    if (results.size() == 0) {
      return null;
    }
    else {
      List resultList = results.toList();
      Collections.shuffle(resultList, new Random())
      return resultList.get(0)
    }
  }

  public Date timeRemaining() {

  }

  public boolean isBattleEntryInRound(BattleEntry battleEntry) {
    return battleEntries.contains(battleEntry)
  }
}
