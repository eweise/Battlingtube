package com.bt.services

import com.bt.domain.Battle
import groovy.text.SimpleTemplateEngine
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import com.bt.domain.Battle
import com.bt.domain.Battle

class BattleRuntimeService {

  boolean transactional = true
  ResourceLoader resourceLoader
  SimpleTemplateEngine templateEngine = new SimpleTemplateEngine()

  def checkBattles() {
    List<Battle> battles = Battle.findAllByState(Battle.STARTED)

    battles.each {Battle battle ->
      battle.checkState()
    }
  }

  //todo not completed
  public void started(Battle battle) {
    Resource resource = resourceLoader.getResource("battle_started.txt")
    Map binding = ["battleName": battle.name]
    Writable writable = templateEngine.createTemplate(resource.file).make(binding)
    String email = writable.toString()
  }


}
