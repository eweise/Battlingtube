package com.bt.services

import com.battlingtube.domain.BattleEvent
import com.battlingtube.util.EmailUtils
import com.bt.domain.Battle
import com.bt.domain.BattleEntry
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener

class BattleEmailService implements ApplicationListener {

  boolean transactional = false

  EventService messagingService
  TemplateService templateService
  boolean sendEmails = ConfigurationHolder.config.bt.sendEmails

  public void onApplicationEvent(final ApplicationEvent event) {
    if (event instanceof BattleEvent) {

      switch (((BattleEvent) event).action) {
        case BattleEvent.Action.created:
          sendBattleCreatedMail event.source
        case BattleEvent.Action.started:
          sendBattleStartedMail event.source
        case BattleEvent.Action.finished:
          sendBattleFinishedMail event.source
        case BattleEvent.Action.roundChanged:
          sendBattleRoundChangedMail event.source
      }
    }
  }

  def sendBattleCreatedMail(Battle battle) {

    String email = templateService.generate("template/battle_created.txt", ["battleName": battle.name])
    mailService.sendMail {
      to battle.creator.profile.email
      subject "Battle ${battle.name} has been created"
      body email

      def emailSet = EmailUtils.parseEmailIntoList(battle.emails) 
      emailSet.each {String emailAddress ->
        String joinEmail = templateService.generate("template/join_invitation.txt",
                ["username": battle.creator.username, "battleId": battle.id, "battleName": battle.name])
        mailService.sendMail {
          to emailAddress
          subject "You have been invited to join battle ${battle.name}"
          body joinEmail
        }
      }
    }
  }

  public void sendBattleStartedMail(Battle battle) {
    if (sendEmails) {
      String email = templateService.generate("template/battle_started.txt", ["battleId": battle.id, "battleName": battle.name])

      // Send email to entries
      battle.battleEntries.each {BattleEntry battleEntry ->
        mailService.sendMail {
          to battleEntry.creator.profile.email
          subject "Battle ${battleEntry.battle.name} has started"
          body email
        }
      }
    }
  }



  public void sendBattleFinishedMail(Battle battle) {
    if (sendEmails) {
      sendFinishedEmailToEntries(battle)
    }
  }

  private def sendFinishedEmailToEntries(Battle battle) {
    String email = templateService.generate("template/battle_finished.txt", ["battleName": battle.name, "battleId": battle.id])

    // Send email to entries
    battle.battleEntries.each {BattleEntry battleEntry ->
      mailService.sendMail {
        to battleEntry.creator.profile.email
        subject "Battle ${battleEntry.battle.name} has finished"
        body email
      }
    }
  }

  private def sendFinishedEmailToVoters(Battle battle) {
    String email = templateService.generate("template/battle_finished.txt", ["battleName": battle.name, "battleId": battle.id])

    // Send email to entries
    battle.battleEntries.each {->
      mailService.sendMail {
        to
        subject "Battle ${battleEntry.battle.name} has finished"
        body email
      }
    }
  }

  public void sendBattleRoundChangedMail(Battle battle) {
    if (sendEmails) {
      String email = templateService.generate("template/battle_roundchanged.txt",
              ["battleName": battle.name, "battleId":battle.id, "lastRound": battle.currentRoundIndex() - 1,
                      "numberOfPlayers": battle.currentRound().battleEntries.size()])

      battle.battleEntries.each {BattleEntry battleEntry ->
        mailService.sendMail {
          to battleEntry.creator.profile.email
          subject "Battle ${battleEntry.battle.name} round has changed"
          body email
        }
      }
    }
  }

}
