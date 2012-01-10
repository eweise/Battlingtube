import com.bt.domain.Battle
import com.bt.domain.BattleEntry
import com.bt.domain.User
import com.bt.domain.Battle

class ActivityController {

    def created = {
        User user = session.user
        [battleList: Battle.findAllByCreator(user)]
    }

    def voted = {
        [battleList: Battle.findVoted(session.user, 20)]
    }

    def joined = {
        def battleEntryList = BattleEntry.findJoined(session.user, 20)
        [battleEntries: battleEntryList]
    }

}
