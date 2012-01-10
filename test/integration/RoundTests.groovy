import com.bt.domain.Round
import com.bt.domain.User
import com.bt.domain.Battle
import com.bt.domain.BattleEntry
import com.bt.domain.Round
import com.bt.domain.User
import com.bt.domain.Battle

class RoundTests extends GroovyTestCase {

    public void testGetBattleEntriesByRanking() {
        User user = TestFixture.createDefaultUser()
        Battle battle = TestFixture.createDefaultBattle()

        BattleEntry be = TestFixture.createDefaultBattleEntry(battle)
        BattleEntry be2 = TestFixture.createDefaultBattleEntry(battle)
        BattleEntry be3 = TestFixture.createDefaultBattleEntry(battle)

        battle.start()

        Round round = battle.currentRound()

        be.createVote(user, 3, "")

        be2.createVote(user, 1, "")

        be3.createVote(user, 2, "")

        List results = round.findRankings()

        assertEquals(3, results.size())

        assertEquals(be2, results[2].battleEntry);
        assertEquals(be3, results[1].battleEntry);
        assertEquals(be, results[0].battleEntry);
    }


    public void testCreate() {
        User user = TestFixture.createDefaultUser()
        Battle battle = TestFixture.createDefaultBattle()
        for (i in 1..6) {
            TestFixture.createDefaultBattleEntry(battle)
        }
        battle.start()

        Round round2 = new Round(battle, 2, 50)

        assertEquals(3, round2.battleEntries.size())
    }

    public void testFindNextRandomByUser() {
        User user = TestFixture.createDefaultUser()
        Battle battle = TestFixture.createDefaultBattleWithEntries(2)
        TestFixture.createDefaultBattleEntry(battle)


        battle.start()
        assertNotNull(battle.currentRound().getRandomEntryForUser(user))
    }
}