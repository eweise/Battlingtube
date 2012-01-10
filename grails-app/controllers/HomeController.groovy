import com.bt.domain.Battle
import com.bt.domain.BattleEntry

class HomeController {


  def index = {
      return featuredBattlePage()
  }

  def member = {
      render view: "member", model: memberPage();

  }

  def featuredBattlePage() {
    def battle = Battle.findByFeatured(true)
    def rankings = battle == null ? null : battle?.currentRound()?.findRankings()

    [battle: battle, rankings: rankings]
  }

  def memberPage() {
    // todo find voted should order by vote date not battle start date
    List voted = Battle.findVoted(session.user, 10)
    List joined = BattleEntry.findJoined(session.user, 20)
    List created = Battle.findBattlesByCreatorOrderedByDate(session.user, 10)

    List activity = new ArrayList();
    activity.addAll(voted)
    activity.addAll(joined)
    activity.addAll(created)

    activity = activity.sort {it.createTime }

    ["activity": activity]

  }

}