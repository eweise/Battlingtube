import com.bt.domain.Battle
import com.bt.domain.BattleEntry
import com.bt.domain.Vote
import com.bt.domain.Battle
import com.bt.domain.BattleEntry
import com.bt.domain.User
import com.bt.domain.Round

class BattleEntryTests extends GroovyTestCase {


    void testAddVote() {
        Battle battle = TestFixture.createDefaultBattleWithEntries(2)
        BattleEntry battleEntry = TestFixture.createDefaultBattleEntry(battle)
        battle.save()
        battle.start()

        battleEntry.createVote(TestFixture.createDefaultUser(), 3, "whatever")
        assertFalse(battleEntry.hasErrors())

        assertEquals(1, battleEntry.votes.size())
        Vote vote = battleEntry.getVotes().iterator().next()
        assertNotNull(vote.id)
    }

    void testFindJoined() {
        Battle battle = TestFixture.createDefaultBattle()
        BattleEntry battleEntry = TestFixture.createDefaultBattleEntry(battle)

        List results = battleEntry.findJoined(battleEntry.creator)
        assertEquals(1, results.size())
        assertTrue(results[0] instanceof BattleEntry)
    }

  void testCountJoined() {
      Battle battle = TestFixture.createDefaultBattle()
      BattleEntry battleEntry = TestFixture.createDefaultBattleEntry(battle)

      int results = battleEntry.countJoined(battleEntry.creator)
      assertEquals(1, results)
  }

  void testFindMostPopular() {
    User user = TestFixture.createDefaultUser()
    User user2 = TestFixture.createDefaultUser()
    for (i in 1..3) {
      Battle battle = TestFixture.createDefaultBattle()
      for (x in 1..5) {
        def be = TestFixture.createDefaultBattleEntry(battle)
      }
    }
    Battle mostVotes = TestFixture.createDefaultBattle()
    for (i in 1..3) {
      TestFixture.createDefaultBattleEntry(mostVotes)
    }
    mostVotes.start()

    Round round = mostVotes.currentRound()
    round.save()
    int score = 10
    round.battleEntries.each {
      it.createVote(user, 3, "comment")
      it.createVote(user2, score--, "comment")
    }
    BattleEntry.findMostPopular(3);
  }

}
