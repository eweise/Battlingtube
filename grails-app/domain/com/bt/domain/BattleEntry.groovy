package com.bt.domain

import com.battlingtube.util.AppUtils

class BattleEntry {

  User creator
  String name
  String youtubeId
  String youtubeUrl;
  String comments
  Battle battle
  Date createTime = AppUtils.currentDate

  Set<Vote> votes = new HashSet<Vote>();

  static hasMany = [votes: Vote]
  static belongTo = [battle: Battle]

  static transients = [ "youtubeUrl" ]

  static constraints = {
    name(blank: true)
    youtubeId(nullable: true)
    comments(nullable: true, blank: true)
  }

  public Vote createVote(User user, int score, String comment) {
    Vote vote = new Vote()
    vote.voter = user
    vote.score = score
    vote.comment = comment
    Round round = battle.currentRound()
    vote.round = round
    vote.battleEntry = this
    round.addToVotes(vote)
    addToVotes(vote)
    vote.save()
    return vote
  }

  public String getYoutubeUrl() {
    return youtubeId==null ? "" : "http://www.youtube.com/watch?v=${youtubeId}"
  }

  public static List<BattleEntry> findJoined(User joiner, int limit) {
    String query = "select distinct be from com.bt.domain.BattleEntry be where be.creator = :joiner order by be.createTime desc"
    return Battle.executeQuery(query, [joiner: joiner, max:limit])
  }

  public static int countJoined(User joiner) {
    String query = "select count(distinct be) from com.bt.domain.BattleEntry be where be.creator = :joiner"
    return Battle.executeQuery(query, [joiner: joiner]).get(0)
  }

  public static List findMostPopular(int limit) {
    Date lastWeek = AppUtils.currentDate - 7;
    String query = "select be.id, avg(v.score)  from com.bt.domain.BattleEntry be join be.votes as v where be.createTime > :lastWeek  group by be order by avg(v.score) desc"
    List results = Vote.executeQuery(query,  [max: limit, lastWeek:lastWeek])

    def battleEntries = new ArrayList();

    results.each { battleEntries.add(BattleEntry.get(it[0]))}
    return battleEntries
  }

  

  public Round findHighestRound() {
    Round highestRound = null;
    for (Round round: battle.rounds) {
      if (round.isBattleEntryInRound(this)) {
        highestRound = round
      }
      else {
        return highestRound;
      }
    }
    return highestRound;
  }

  public String findYoutubeTitle() {
     return "youtube api removed"
 }


  void assignYoutubeIdFromUrl(String url) {
    if (url == null || url.trim().size() == 0) {
      errors.reject("battleEntry.youtubeUrl.blank")
      return
    }

    if (!url.contains("v=")) {
      errors.reject("battleEntry.youtubeUrl.badformat")
      return
    }

    int startpos = url.indexOf("v=") + 2
    int endpos = url.size();
    String idString = url.substring(startpos, endpos)
    if (idString == null || idString.isEmpty()) {
      errors.reject("battleEntry.youtubeUrl.badformat")
      return
    }
    youtubeUrl = url
    youtubeId = idString;
  }


}
