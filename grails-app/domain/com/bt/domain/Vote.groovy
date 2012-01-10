package com.bt.domain
class Vote {

    Date createDate = new Date()
    int score
    String comment
    User voter
    BattleEntry battleEntry
    Round round

    static belongTo = [BattleEntry, Round]


}
