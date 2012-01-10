import com.bt.domain.Battle
import com.bt.domain.BattleEntry
import grails.converters.JSON
import org.springframework.validation.Errors

class BattleEntryController {

  def index = { redirect(action: list, params: params) }

  // the delete, save and update actions only accept POST requests
  static def allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

  def details = {
    def id = params.id
    BattleEntry battleEntry = BattleEntry.get(id)

    if (!battleEntry) {
      flash.message = "BattleEntry not found with id ${params.id}"
      redirect(action: list)
    }
    else { return [battleEntry: battleEntry, ranking: battleEntry.findHighestRound()?.findRanking(battleEntry)] }
  }

  def deleteEntry = {
    def battleEntry = BattleEntry.get(params.id)
    if (battleEntry) {
      def name = battleEntry.name
      battleEntry.delete()
      flash.message = "BattleEntry '${name}' deleted"
      redirect(controller: "activity", action: "joined")
      return
    }
    flash.message = "BattleEntry not found with id ${params.id}"
  }

  def create = {
    def battleEntry = new BattleEntry()
    def battle = Battle.get(params.id)
    battleEntry.properties = params
    render view: "edit", model: ['battleEntry': battleEntry, "battle": battle]
  }

  def edit = {

    def battleEntry = BattleEntry.get(params.id)

    if (!battleEntry) {
      flash.message = "BattleEntry not found with id ${params.id}"
      redirect(controller: "activity", action: "joined")
      return
    }

    [battleEntry: battleEntry, battle: battleEntry.battle]
  }

  def save = {
    BattleEntry battleEntry = new BattleEntry(params)
    Battle battle = Battle.get(params.battleId)

    boolean isNew = true

    if (params.id) {
      battleEntry = BattleEntry.get(params.id)
      isNew = false
    }

    String url = params.youtubeUrl
    battleEntry.assignYoutubeIdFromUrl(url)
    if (battleEntry.hasErrors()) {
      render(view: 'edit', model: ["battleEntry": battleEntry, "battle": battle])
      return
    }

    battleEntry.name = battleEntry.findYoutubeTitle()
    if (battleEntry.hasErrors()) {
      render(view: 'edit', model: ["battleEntry": battleEntry, "battle": battle])
      return
    }

    battleEntry.comments = params.comments

    if (battleEntry.hasErrors()) {
      render(view: 'edit', model: ["battleEntry": battleEntry, "battle": battle])
      return
    }
    if (params.id !=null) {
      battleEntry.creator = session.user
      battle.addToBattleEntries(battleEntry);
    }

    battleEntry.validate()

    if (!battleEntry.hasErrors() && battleEntry.save()) {
      battle.save()
      flash.message = "Video '${battleEntry.name}' has been added to battle ${battle.name}"
      redirect(controller: "activity", action: "joined")

    }
    else {
      render(view: 'edit', model: ["battleEntry": battleEntry, "battle": battle])
    }
  }


}