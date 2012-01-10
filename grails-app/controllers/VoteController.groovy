import com.bt.domain.Battle
import com.bt.domain.Vote
import com.bt.domain.BattleEntry
import com.bt.domain.Battle
import com.bt.domain.Vote
import com.bt.domain.BattleEntry
import com.battlingtube.domain.QuerySpec
import com.bt.domain.User
import com.battlingtube.util.ControllerHelper
import com.battlingtube.util.AppUtils

class VoteController {

    def list = {
        int page = params.page == null ? 1 : Integer.parseInt(params.page)
        QuerySpec spec = new QuerySpec(page, 5, "sortField", true);
        [queryResult: Battle.findStarted(spec)]
    }

    def next = {
        Battle battle = Battle.get(params.id)
        if (battle == null) {
            return
        }

        def battleEntry = battle.getRandomEntryForUser(ControllerHelper.findUser(session, request, response))

        if (battleEntry == null) {
            flash.message = "You have voted for all contestants.  Thank you"
            redirect(controller: "battle", action: "details", params: [id: battle.id])
            return
        }

        // get all the votes so far for the user
        List votingRecord = Vote.findAllByRoundAndVoter(battle.currentRound(), ControllerHelper.findUser(session, request, response), [sort: "createDate", order: "desc"])

        int entriesRemaining = battle.currentRound().battleEntries.size() - votingRecord.size()
        render(view: "vote", model: [Battle: battle, battleEntry: battleEntry, vote: new Vote(), votingRecord: votingRecord, entriesRemaining: entriesRemaining])
    }



    def vote = {
        def battleEntryId = params["battleEntryId"]
        BattleEntry battleEntry = BattleEntry.get(battleEntryId)
        if (battleEntry == null) {
            return
        }
        Vote command = new Vote(params['vote'])
        command.comment = AppUtils.stripInvalidText(command.comment)
        if (params['vote.score'] == null) {
            command.errors.rejectValue("score", "", "Score was not entered")
            render(view: "vote", model: [battleEntry: battleEntry, vote: command])
            return
        }

        // If no user logged in then just create/associate with guest user
        Vote vote = battleEntry.createVote(ControllerHelper.findUser(session, request, response), command.score, command.comment)
        if (vote.hasErrors()) {
            render(view: "vote", model: [battleEntry: battleEntry, vote: command])
            return
        }

        redirect(controller: "vote", action: "next", id: battleEntry.battle.id)


    }


}
