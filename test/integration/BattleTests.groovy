import TestFixture
import com.battlingtube.domain.InsufficientNumberOfEntriesException
import com.battlingtube.util.AppUtils
import com.bt.services.BattleRuntimeService
import com.bt.domain.*
import com.bt.domain.reference.Category
import com.bt.domain.reference.Category

class BattleTests extends GroovyTestCase {

  BattleRuntimeService battleRuntimeService

  void testGetRandomEntryForUser() {
    User voter = TestFixture.createDefaultUser()
    Battle battle = TestFixture.createDefaultBattle()
    for (i in 0..2) {
      TestFixture.createDefaultBattleEntry(battle)
    }
    battle.save(flush: true)
    battle.start()

    assertNotNull(battle.getRandomEntryForUser(voter))

  }

  void testFindByTextBattleName() {
    TestFixture.createDefaultBattle().name = "testName"
    List results = Battle.findByText("testName",2)
    boolean testNameFound = false;
    results.each {if(it.name == "testName") testNameFound = true}
    assertTrue(testNameFound)
  }

  void testFindByTextUsername() {
    Battle battle = TestFixture.createDefaultBattle()
    battle.creator.username = "test"
    List results = Battle.findByText("test",2)
    boolean testNameFound = false;
    results.each {if(it.creator.username == "test") testNameFound = true}
    assertTrue(testNameFound)
  }

  void testFindByTextBattleDescription() {
    TestFixture.createDefaultBattle().description = "testName"
    List results = Battle.findByText("testName",2)
    boolean testNameFound = false;
    results.each {if(it.description == "testName") testNameFound = true}
    assertTrue(testNameFound)
  }

  void testFindByTextBattleCategory() {
    Battle battle = TestFixture.createDefaultBattle()
    Category testCategory = new Category("test")
    testCategory.save(flush:true)
    battle.category = testCategory
    battle.save(flush:true)
    List results = Battle.findByText("test",2)
    boolean testNameFound = false;
    results.each {if(it.category.name == "test") testNameFound = true}
    assertTrue(testNameFound)
  }


  void testBattleWithMostVotes() {
    User user = TestFixture.createDefaultUser()
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
    round.battleEntries.each {
      Vote vote = it.createVote(user, 3, "comment")
    }


    Battle result = Battle.findStartedWithMostVotes()
    assertEquals(result.id, mostVotes.id)
  }



  void testRoundWithOneEntryIsWinner() {
    Battle battle = TestFixture.createDefaultBattle()
    battle.numberOfRounds = 2
    TestFixture.createDefaultBattleEntry(battle)
    battle.save(flush: true)

    try {
      battle.start()
    }
    catch (InsufficientNumberOfEntriesException e) {
      TestFixture.createDefaultBattleEntry(battle)
      battle.save(flush: true)
      battle.start()

      AppUtils.advanceDateNumberDays 2
      battle.checkState()
      assertTrue(battle.finished())
      return
    }
    fail()
  }

  void testFindCreated() {
    User user = TestFixture.createDefaultUser()
    Battle battle = TestFixture.createDefaultBattle()
    battle.creator = user
    battle.save(flush: true)

    Battle battle2 = TestFixture.createDefaultBattle()
    battle2.creator = user
    battle2.save(flush: true)

    assertEquals(2, Battle.findCreated(user).size())
  }

  void testCountCreated() {
    User user = TestFixture.createDefaultUser()
    Battle battle = TestFixture.createDefaultBattle()
    battle.creator = user
    battle.save(flush: true)

    Battle battle2 = TestFixture.createDefaultBattle()
    battle2.creator = user
    battle2.save(flush: true)

    assertEquals(2, Battle.countCreated(user))
  }


  void testFindVotedAndCountVoted() {
    User voter = TestFixture.createDefaultUser()
    Battle battle = TestFixture.createDefaultBattle()
    TestFixture.createDefaultBattleEntry(battle)
    TestFixture.createDefaultBattleEntry(battle)
    battle.start()
    BattleEntry battleEntry = TestFixture.createDefaultBattleEntry(battle)
    battleEntry.save(flush: true)
    battleEntry.createVote(voter, 2, "")
    battleEntry.createVote(voter, 2, "")
    battleEntry.save(flush: true)

    assertEquals(1, Battle.findVoted(voter).size())

    BattleEntry battleEntry2 = TestFixture.createDefaultBattleEntry(battle)
    battleEntry2.save(flush: true)
    battleEntry2.createVote(voter, 2, "")
    battleEntry2.save(flush: true)

    assertEquals(1, Battle.findVoted(voter).size())
    assertEquals(1, Battle.countVoted(voter))
  }


  void testAddRound() {
    Battle battle = TestFixture.createDefaultBattle()
    TestFixture.createDefaultBattleEntry(battle)
    TestFixture.createDefaultBattleEntry(battle)
    TestFixture.createDefaultBattleEntry(battle)

    battle.numberOfRounds = 3
    battle.percentageToNextRound = 4
    battle.start()
    Round round = battle.addRound()
    assertFalse(battle.hasErrors())
    assertFalse(round.hasErrors())
    assertEquals(3, round.battleEntries.size())
    assertEquals(1, battle.rounds.size())
    assertNotNull(round.startDate)
    assertNotNull(round.endDate)
    assertTrue(round.endDate.after(round.startDate))

  }

  void testFindMostRecentlyFinished() {
    Battle b1 = TestFixture.createDefaultBattleWithEntries(2)
    b1.category = Category.findByTerm(Category.COMEDY)
    Battle b2 = TestFixture.createDefaultBattleWithEntries(2)
    b2.category = Category.findByTerm(Category.EDUCATION)
    Battle b3 = TestFixture.createDefaultBattleWithEntries(2)
    b3.category = Category.findByTerm(Category.ENTERTAINMENT)
    Battle b4 = TestFixture.createDefaultBattleWithEntries(2)
    b4.category = Category.findByTerm(Category.MUSIC)

    b2.start()
    b3.start()
    b4.start()
    b1.start()


    List finishedFirstCheck = Battle.findMostRecentlyFinished(3)

    assertEquals(0, finishedFirstCheck.size())

    AppUtils.advanceDateNumberDays 2

    battleRuntimeService.checkBattles()

    List finishedSecondCheck = Battle.findMostRecentlyFinished(3)

    assertEquals(3, finishedSecondCheck.size())


  }


  void testFindMostRecentlyStarted() {
    Battle comedy = TestFixture.createDefaultBattleWithEntries(2)
    comedy.category = Category.findByTerm(Category.COMEDY)
    Battle education = TestFixture.createDefaultBattleWithEntries(2)
    education.category = Category.findByTerm(Category.EDUCATION)
    Battle entertainment = TestFixture.createDefaultBattleWithEntries(2)
    entertainment.category = Category.findByTerm(Category.ENTERTAINMENT)

    education.start()
    entertainment.start()
    comedy.start()

    List started = Battle.findMostRecentlyStarted(3)

    assertEquals(3, started.size())
    assertTrue(started.contains(education))
    assertTrue(started.contains(entertainment))
    assertTrue(started.contains(comedy))

    List startedComedy = Battle.findMostRecentlyStarted((Category) Category.findByTerm(Category.COMEDY), 1)
    assertEquals(1, startedComedy.size())
    assertTrue(startedComedy.contains(comedy))
  }

  void testFindMostRecentlyCreated() {

    Battle b1 = TestFixture.createDefaultBattleWithEntries(2)
    b1.category = Category.findByTerm(Category.COMEDY)
    AppUtils.advanceDateNumberDays 1
    Battle b2 = TestFixture.createDefaultBattleWithEntries(2)
    b2.category = Category.findByTerm(Category.COMEDY)
    AppUtils.advanceDateNumberDays 1
    Battle b3 = TestFixture.createDefaultBattleWithEntries(2)
    b3.category = Category.findByTerm(Category.ENTERTAINMENT)
    AppUtils.advanceDateNumberDays 1
    Battle b4 = TestFixture.createDefaultBattleWithEntries(2)
    b4.category = Category.findByTerm(Category.EDUCATION)

    List started = Battle.findMostRecentlyCreated(3)

    assertEquals(3, started.size())
    assertTrue(started.contains(b2))
    assertTrue(started.contains(b3))
    assertTrue(started.contains(b4))

    AppUtils.advanceDateNumberDays 1
    List createdPop = Battle.findMostRecentlyCreated((Category) Category.findByTerm(Category.ENTERTAINMENT), 3)
    assertEquals(1, createdPop.size())
    assertTrue(createdPop.contains(b3))
  }

  void testFindNotStarted() {
    TestFixture.createDefaultBattleWithEntries(2)
    TestFixture.createDefaultBattleWithEntries(2)
    Battle battle2 = TestFixture.createDefaultBattleWithEntries(2)

    int notStarted = Battle.findNotStarted().size()
    battle2.start()
    battle2.save(flush:true)
    assertEquals(notStarted-1, Battle.findNotStarted().size())
  }

  void testFindStarted() {
    TestFixture.createDefaultBattle()
    TestFixture.createDefaultBattle()
    Battle battle3 = TestFixture.createDefaultBattleWithEntries(2)
    Battle battle2 = TestFixture.createDefaultBattleWithEntries(2)

    int started = Battle.findStarted().size()
    battle2.start()
    battle3.start()
    assertEquals(started+2, Battle.findStarted().size())
  }

  void testStart() {
    Battle battle = TestFixture.createDefaultBattleWithEntries(2)
    battle.start()
    assertTrue(battle.started())
    assertEquals(1, battle.currentRoundIndex())
  }

  void testCheckState() {
    Battle battle = TestFixture.createDefaultBattleWithEntries(2)
    battle.start()
    battle.checkState()
    assertTrue(battle.started())
    AppUtils.advanceDateNumberDays 2
    battle.checkState()
    assertTrue(battle.finished())
  }

  void testAddComment() {
    Battle battle = TestFixture.createDefaultBattleWithEntries(2)
    User voter = TestFixture.createDefaultUser()
    battle.commenter().newComment("test", voter);
    battle.save();
    assertEquals(battle.comments.size(), 1)
  }

  void testAddReply() {
    Battle battle = TestFixture.createDefaultBattleWithEntries(2)
    User voter = TestFixture.createDefaultUser()
    Comment comment = battle.commenter().newComment("test", voter);
    battle.save(flush:true);

    Comment reply = battle.commenter().replyTo(comment, "test me", voter)
    battle.save(flush:true)
    assertTrue(comment.getReplies().contains(reply))

  }

}
