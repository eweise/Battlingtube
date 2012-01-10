import com.bt.domain.Battle
import com.bt.domain.reference.Category
import com.bt.domain.Vote
import org.springframework.validation.Errors
import com.bt.domain.Battle
import com.bt.domain.reference.Category
import com.bt.domain.Battle
import com.bt.domain.reference.Category
import com.bt.domain.reference.Category
import com.bt.domain.reference.Category
import com.battlingtube.domain.BattleEvent
import com.bt.services.EventService
import com.battlingtube.util.ControllerHelper

class BattleController {

  EventService eventService
  
  def index = { redirect(action: list, params: params) }

  // the delete, save and update actions only accept POST requests
  static def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

  def search = {
    [battleList: Battle.findNotStarted()]
  }

  def details = {
    def battle = Battle.get(params.id)
    if (!battle) {
      redirect(controller: "home", action: "index")
    }

    if(battle.state == Battle.STARTED && battle.getRandomEntryForUser(ControllerHelper.findUser(session, request, response))!=null) {
      redirect(controller:"vote", action:"next", params:["id":battle.id])
    }

    def rankings = battle == null ? null : battle?.currentRound()?.findRankings()

    // get all the votes so far for the user
    List votingRecord = Vote.findAllByRoundAndVoter(battle.currentRound(), session.user, [sort: "createDate", order: "desc"])

    return [battle: battle, rankings: rankings, votingRecord: votingRecord]
  }

  def cancel = {
    Battle battle = Battle.get(params.id)
    if (battle) {
      String battleName = battle.name
      battle.cancel()
      Errors errors = battle.errors
      flash.message = "Battle \"${battleName}\" deleted"
      redirect(controller: "activity", action: "created")
    }
    else {
      flash.message = "Battle not found with id ${params.id}"
      redirect(action: list)
    }
  }

  def edit = {
    def battle = Battle.get(params.id)

    if (!battle) {
      flash.message = "Battle not found with id ${params.id}"
      redirect(action: list)
    }
    else {
      return [battle: battle, categories: Category.list()]
    }
  }

  def update = {
    def battle = Battle.get(params.id)
    if (battle) {
      battle.properties = params
      if (!battle.hasErrors() && battle.save()) {
        flash.message = "Battle '${params.id}' updated"
        redirect(action: list)
      }
      else {
        render(view: 'edit', model: [battle: battle])
      }
    }
    else {
      flash.message = "Battle not found with id ${params.id}"
      redirect(action: edit, id: params.id)
    }
  }

  def create = {
    def battle = new Battle()
    battle.properties = params
    render(view: "edit", model: ['battle': battle, 'categories': Category.listOrderByName()])
  }

  def save = {
    def battle = new Battle()
    if (params.id) {
      battle = Battle.get(params.id)
    }
    else {
      battle.creator = session.user
    }
    battle.properties = params

    battle.validate()
    if (!battle.hasErrors()) {
      battle.save()
      flash.message = "Battle '${battle.name}' created"
      eventService.sendEvent new BattleEvent(battle, BattleEvent.Action.created)
      redirect(controller: "activity", action: "created")
    }
    else {
      render(view: 'edit', model: ['battle': battle, 'categories':Category.list()])
    }
  }

  def start = {
    Battle battle = Battle.get(params.id)
    if (battle) {
      battle.start()
      flash.message = "Battle '${battle.name}' started "
      redirect(controller: "activity", action: "created")
    }
    else {
      flash.message = "Battle not found with id ${params.id}"
      redirect(action: list)
    }
  }


  private void upload(def file, Battle battle) {
  }

}