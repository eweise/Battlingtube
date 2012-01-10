package com.battlingtube.util

import com.bt.domain.Round
import com.bt.domain.BattleEntry
import com.bt.domain.BattleEntry

class RoundUnitTests extends GroovyTestCase {

    void testPastEndDate() {
        Round round = new Round();
        Calendar cal = new GregorianCalendar()
        cal.set(Calendar.YEAR, 2001)
        cal.set(Calendar.MONTH, 3)
        cal.set(Calendar.DAY_OF_MONTH, 3)

        round.endDate = cal.time
        Thread.sleep 100
        assertTrue(round.pastEndDate())
    }


    void testBattleEntryInRound() {
        Round r = new Round()
        BattleEntry be = new BattleEntry()
        r.battleEntries.add(be)
        assertTrue(r.isBattleEntryInRound(be))
    }


}