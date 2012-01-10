import com.bt.domain.Battle
import com.bt.domain.CreateBattleCriteria

public class CreateBattleJob {


  def execute(context) {
    CreateBattleCriteria criteria = context.mergedJobDataMap.get('criteria')

    if(criteria == null) {
      return
    }
    log.info("autoCreateBattlesService.createBattles started")
//    autoCreateBattlesService.createBattles criteria
    log.info("autoCreateBattlesService.createBattles finished")
   }
}