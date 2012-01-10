import com.bt.domain.Battle
import com.bt.domain.BattleEntry
import com.bt.domain.reference.Category
import com.bt.domain.User
import org.springframework.validation.Errors
import com.bt.domain.reference.Category
import com.bt.domain.reference.Category
import com.bt.domain.reference.Category
import com.bt.domain.UserProfile

class TestFixture {

  static long counter = 0

  public static User createDefaultUser() {
    User user = new User();
    user.username = "test${getNextCount()}"

    UserProfile profile = new UserProfile()
    user.profile = profile
    profile.email = "test${getNextCount()}@test.com"
    profile.password = "pass${getNextCount()}"
    user.save(flush: true);
    checkErrors(user)
    return user
  }


  public static BattleEntry createDefaultBattleEntry(Battle battle) {
    User joiner = TestFixture.createDefaultUser()

    BattleEntry battleEntry = new BattleEntry()
    battleEntry.creator = joiner
    battleEntry.name = "name" + getNextCount()
    battleEntry.youtubeId = "youtubeid"
    battle.addToBattleEntries(battleEntry)
    battle.save(flush: true)
    checkErrors(battle)

    return battleEntry
  }

  public static Battle createDefaultBattleWithEntries(int numberOfEntries) {
    Battle battle = createDefaultBattle()

    for (i in 0..numberOfEntries) {
      createDefaultBattleEntry(battle)
    }
    return battle
  }

  public static Battle createDefaultBattle() {
    Battle battle = new Battle()
    battle.name = "name${getNextCount()}"
    battle.description = "description${getNextCount()}"
    battle.save(flush:true)
    battle.category = getAnyCategory()
    battle.creator = createDefaultUser()
    battle.numberOfRounds = 1
    battle.save(flush: true)
    checkErrors(battle)
    return battle
  }


  public static Category getAnyCategory() {
    Category.findAll().get(0)
  }

  private static def checkErrors(GroovyObject go) {
    Errors errors = go.errors;
    if (errors.errorCount > 0) {
      throw new RuntimeException("${go} has errors")
    }
  }


  public static clearBattles() {
    Battle.findAll().each {
      it.delete()
      checkErrors(it)
    }
  }


  static long getNextCount() {
    return counter++
  }

}