import com.bt.domain.Battle

class AdminController {


  def pickFeaturedBattle() {
    Battle.findAllByFeatured(true).each { it.featured = false }
    Battle.get(params.id).featured = true
  }
}
