import com.bt.services.BattleRuntimeService

class CheckBattlesJob {

    def startDelay = 1000 * 60 * 10  // 10 minutes
    def timeout = 30000  // 30 seconds

    def group = "MyGroup"

    BattleRuntimeService battleRuntimeService

    def execute() {
        log.info("checkBattles started")
        battleRuntimeService.checkBattles()
        log.info("checkBattles ended")
    }

}