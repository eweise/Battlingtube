import CreateBattleJob
import com.battlingtube.util.AppUtils
import com.bt.domain.reference.Category
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import com.bt.domain.*

class BootStrap {

  def init = {servletContext ->

    boolean loadTestData = ConfigurationHolder.config.bt.loadTestData;
    addCategoriesIfNonExistent()
    if (loadTestData && Battle.findAll().size() == 0) {
      User user = new User(admin: true)
      user.setProfile(new UserProfile(username: "ew", email: "name@email.com", password: "ew"))
      user.save(flush: true)

      createBattle(user, "Cat Tricks", "Best cat tricks", 3, Category.ANIMALS, false)
      createBattle(user, "Funny Faces", "Do you funniest face", 4, Category.COMEDY, true).start()
      createBattle(user, "Short Film", "Best short film", 2, Category.FILM, false).start()
      createBattle(user, "Guitar Solo", "post your wickedest guitar solo", 3, Category.MUSIC, false).start()
      createBattle(user, "Biggest Lie", "Post the biggest lie caught on video", 1, Category.NEWS, false)
      createBattle(user, "Jokes", "Tell your best joke", 1, Category.COMEDY, false)
      createBattle(user, "Embarassing moments", "share your most embarassing moment", 1, Category.COMEDY, false)
      createBattle(user, "Exotic Places", "Most exotic place you have been", 1, Category.TRAVEL, false).start()
      createBattle(user, "Burping", "Longest burp", 1, Category.COMEDY, false).start()

      //advance 2 days
      AppUtils.advanceDateNumberDays 2

    }

    addEventListeners()

    if(!AppUtils.isInTestMode()) {
      initializeAutoCreateBattles()
    }

  }

  private addCategoriesIfNonExistent() {
    addCategoryIfNonExistent(Category.COMEDY, "Comedy")
    addCategoryIfNonExistent(Category.AUTOS, "Autos & Vehicles")
    addCategoryIfNonExistent(Category.EDUCATION, "Education")
    addCategoryIfNonExistent(Category.ENTERTAINMENT, "Entertainment")
    addCategoryIfNonExistent(Category.FILM, "Film & Animation");
    addCategoryIfNonExistent(Category.GAMING, "Games");
    addCategoryIfNonExistent(Category.HOWTO, "Howto & Style");
    addCategoryIfNonExistent(Category.MUSIC, "Music")
    addCategoryIfNonExistent(Category.NEWS, "News & Politics")
    addCategoryIfNonExistent(Category.NONPROFITS, "Nonprofits & Activism")
    addCategoryIfNonExistent(Category.PEOPLE, "People & Blogs")
    addCategoryIfNonExistent(Category.ANIMALS, "Pets & Animals")
    addCategoryIfNonExistent(Category.TECH, "Science & Technology")
    addCategoryIfNonExistent(Category.SPORTS, "Sports")
    addCategoryIfNonExistent(Category.TRAVEL, "Travel and Events");
    addCategoryIfNonExistent(Category.ALL, "All")
  }

  private void addCategoryIfNonExistent(String term, String name) {
    if (Category.findByTerm(term) == null) {
      loadReferenceData(new Category(term: term, name: name))
    }
  }

  private loadReferenceData(def entity) {
    entity.save(flush: true);
  }

  private def createBattleEntries(Battle battle1, User ew) {
    createBattleEntry(battle1, ew, "High FiveXXXXXXXX XXXXXXXX XXXXXXXXX XXXXXXXXXXXXXX", "My cat love to give high fivesTTTTTTTTTTTTTTT ", "3gKPpXkPzFA")
    createBattleEntry(battle1, ew, "Bad CatXXXXXXXXXXX XXXXXXXX XXXXXXXXXXX XXXXXXXXXX", "bad kitty TTTTTTTTTTTTTTTTTTTTT", "VZCvtrD8xwM")
    createBattleEntry(battle1, ew, "Play dead XXXXXXXXX XXXXXXXXXXXXX XXXXXXXXXXXXXXX", "its bull terrier cat TTTTTTTTTTTTTTTTTT", "Ii3fzZJUCw4")
    createBattleEntry(battle1, ew, "On the swingxxxxxxxxx xxxxxxxxxxxxx xxxxxxxxxxx xxxxxxxxxxxxxxxxxxx", "I don't have a lifeTTTTTTTTTTTTTTTTTTTTT", "xG3kBq7hZ3M")
  }

  def createBattleEntry(Battle battle, User creator, String name, String comments, String youtubeId) {
    BattleEntry battleEntry = new BattleEntry()
    battleEntry.creator = creator
    battleEntry.name = name
    battleEntry.comments = comments
    battleEntry.youtubeId = youtubeId
    battle.addToBattleEntries(battleEntry)
    battle.save()
    return battleEntry
  }

  def createBattle(User user, String name, String description, int rounds, String categoryTerm, boolean featured) {
    Battle battle = new Battle()
    battle.creator = user
    battle.name = name
    battle.description = description
    battle.numberOfRounds = rounds
    battle.category = Category.findByTerm(categoryTerm)
    battle.featured = featured
    battle.save(flush: true);
    createBattleEntries(battle, user)
    return battle
  }

  private void initializeAutoCreateBattles() {
    initializeAutoCreateBattle "Top 10 Education Videos of the Week", Category.EDUCATION, true, 10, 1, 7, 10, "0 0 12 ? * MON"
    initializeAutoCreateBattle "Top 10 People Videos of the Week", Category.PEOPLE, true, 10, 1, 7, 10, "0 0 10 ? * MON"
    initializeAutoCreateBattle "Top 10 Entertainment Videos of the Week", Category.AUTOS, true, 10, 1, 7, 10, "0 0 12 ? * TUE"
    initializeAutoCreateBattle "Top 10 Technology Videos of the Week", Category.TECH, true, 10, 1, 7, 10, "0 0 10 ? * TUE"
    initializeAutoCreateBattle "Top 10 Movie Videos of the Week", Category.FILM, true, 10, 1, 7, 10, "0 0 12 ? * WED"
    initializeAutoCreateBattle "Top 10 Sports Videos of the Week",  Category.SPORTS, true, 10, 1, 7, 10, "0 0 10 ? * WED"
    initializeAutoCreateBattle "Top 10 Gaming Videos of the Week", Category.GAMING, true, 10, 1, 7, 10, "0 0 12 ? * THU"
    initializeAutoCreateBattle "Top 10 Travel Videos of the Week", Category.TRAVEL, true, 10, 1, 7, 10, "0 0 10 ? * THU"
    initializeAutoCreateBattle "Top 10 Automotive Videos of the Week", Category.AUTOS, true, 10, 1, 7, 10, "0 0 12 ? * FRI"
    initializeAutoCreateBattle "Top 10 Comedy Videos of the Week", Category.COMEDY, true, 10, 1, 7, 10, "0 0 10 ? * FRI"
    initializeAutoCreateBattle "Top 10 'How To' Videos of the Week", Category.HOWTO, true, 10, 1, 7, 10, "0 0 12 ? * SAT"
    initializeAutoCreateBattle "Top 10 Pet & Animal Videos of the Week", Category.ANIMALS, true, 10, 1, 7, 10, "0 0 12 ? * SUN"
    initializeAutoCreateBattle "Top 10 Non Profit Videos of the Week", Category.NONPROFITS, true, 10, 1, 7, 10, "0 0 10 ? * SUN"

    initializeAutoCreateBattle "News of the Day", Category.NEWS, false, 5, 1, 1, 10, "0 0 8 * * ?"

  }
  private void initializeAutoCreateBattle( String name, String category, boolean forWeek, int maxResults, int rounds, int duration, int perc, String schedule) {
    // Most popular videos of the week
    CreateBattleCriteria criteria = new CreateBattleCriteria(category)
    criteria.name = name
    criteria.category = category
    criteria.numberOfRounds = rounds
    criteria.roundDuration = duration
    criteria.percentageToNextRound = perc

    criteria.setTime forWeek ? Time.THIS_WEEK : Time.TODAY
    criteria.setMaxResults maxResults


    Map params = new HashMap()
    params.put("criteria", criteria)

  AppUtils.isInTestMode() ? CreateBattleJob.triggerNow(params) : CreateBattleJob.schedule(schedule, params)

  }

  def destroy = {
  }


  def addEventListeners() {

  }
}