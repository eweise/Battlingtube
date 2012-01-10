package com.battlingtube.util

import com.bt.domain.Round
import com.bt.domain.BattleEntry
import com.bt.domain.Battle
import com.bt.domain.BattleEntry

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Jan 6, 2009
 * Time: 2:36:10 PM
 * To change this template use File | Settings | File Templates.
 */

public class BattleEntryUnitTests extends GroovyTestCase {

    void testHighestRound() {
        Round r1 = new Round()
        Round r2 = new Round()
        Round r3 = new Round()
        Battle b = new Battle()
        BattleEntry be = new BattleEntry()
        b.rounds.add(r1)
        b.rounds.add(r2)
        b.rounds.add(r3)

        be.battle = b
        r1.battleEntries.add(be)
        r2.battleEntries.add(be)

        assertEquals(r2, be.findHighestRound())
    }

}