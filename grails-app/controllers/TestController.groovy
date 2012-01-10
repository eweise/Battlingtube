import com.bt.services.BattleRuntimeService
import com.battlingtube.util.AppUtils
import com.bt.services.BattleRuntimeService
import com.bt.domain.reference.Category

class TestController {

  BattleRuntimeService battleRuntimeService


  def index = {
    flash.message = "Current Date is ${AppUtils.currentDate}"
  }

  def advanceDays = {
    AppUtils.advanceDateNumberDays Integer.parseInt(params.days)
    flash.message = "Saved!  The date is now ${AppUtils.getCurrentDate()}"
    redirect(controller: "test", view: "index")
  }

  def checkstate = {
    battleRuntimeService.checkBattles()
    render(view: "index")
  }

  def autoCreateBattles = {
    String autocreate = params.autocreate

    flash.message = "Auto Create Battle is now ${autocreate}"
    redirect(view: "actions.gsp")

  }
}
