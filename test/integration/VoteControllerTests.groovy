import com.bt.domain.Battle
import com.bt.domain.User

class VoteControllerTests extends GroovyTestCase {

    User user;
    Battle battle


    // Class under test
    def controller = new VoteController()

    public void setUp() {

        battle = TestFixture.createDefaultBattle()

        for (i in 1..3) {
            TestFixture.createDefaultBattleEntry(battle).save()
        }

        battle.start()

        user = TestFixture.createDefaultUser()
        controller.session.user = user
    }

    void testNext() {
        nextWithAssertions()
    }

    private def nextWithAssertions() {
        controller.params.id = battle.id

        controller.next()
        assertNextIsInitialized()
    }

    private def assertNextIsInitialized() {
        def mv = controller.modelAndView
        assertEquals "/vote/vote", mv.viewName

        def battleEntry = controller.modelAndView.model.battleEntry
        assertNotNull(battleEntry)

        def vote = controller.modelAndView.model.vote
        assertNotNull(vote)
        assertNull(vote.comment)
        assertNull(vote.id)
    }


    void testNextAndVote() {
        nextWithAssertions()
        assertNextIsInitialized()

        def battleEntry = controller.modelAndView.model.battleEntry
        controller.params.battleEntryId = battleEntry.id
        initializeVote(2, "yo")
        controller.vote()
        battleEntry = controller.modelAndView.model.battleEntry
        assertEquals(1, battleEntry.votes.size())

    }

    def initializeVote(int score, String comment) {
        controller.params.vote = ['score':score, 'comment':comment]
        controller.params['vote.score'] = score
        controller.params['vote.comment'] = comment
    }
}
