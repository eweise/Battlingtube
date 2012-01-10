import com.bt.domain.Battle
import com.bt.domain.Battle
import com.bt.domain.Battle
import com.bt.domain.BattleEntry

class BrowseController {

  def newbattles = {
    def battles = Battle.findMostRecentlyCreated(100)
    [battles: battles]
  }

  def startedbattles = {
    [battles: Battle.findMostRecentlyStarted(100)]
  }

  def finishedbattles = {
    [battles: Battle.findMostRecentlyFinished(100)]
  }

  def topvideos = {
    [entries: BattleEntry.findMostPopular(10)]
  }
}
