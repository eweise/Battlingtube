package com.bt.domain

import com.bt.domain.reference.Category
import java.sql.Time

/**
 * Created by IntelliJ IDEA.
 * User: eweise
 * Date: Jan 11, 2010
 * Time: 7:33:44 AM
 * To change this template use File | Settings | File Templates.
 */

public class CreateBattleCriteria {

//  YouTubeQuery query
  String name
  String category
  int numberOfRounds
  int roundDuration
  int percentageToNextRound
  int maxResults

  public CreateBattleCriteria(String category) {
    // youtube api removed
  }

  def setTime(Time time) {
    query.setTime time
  }

}