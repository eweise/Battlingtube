import com.bt.domain.User
import com.bt.domain.UserProfile
import com.battlingtube.util.ControllerHelper

class UserController {


  def scaffold = true

  def login = {

  }


  def doLogin = {

    User user = User.findUserByCredentials(params.username, params.password)


    if (user) {
      UserProfile p = user.profile
      session.user = user
      session.username = user.username


      def redirectParams =
      session.originalRequestParams ?
        session.originalRequestParams :
        [controller: 'home']
      redirect(redirectParams)

    }
    else {
      flash.message = "Invalid name or password"
      redirect(controller: "user", action: 'login')
    }
  }

  def logout = {
    session.invalidate()
    redirect(controller: "home", view: "index")
  }

  def register = {

    if (request.method == "POST") {
      UserProfile profile = new UserProfile(params)

      if (profile.password != profile.password2) {
        profile.errors.reject("user.password.mismatch")
        render(view: "register")
        return
      }

      boolean acceptTerms = Boolean.parseBoolean(params.terms)
      if (!acceptTerms) {
        profile.errors.reject("You must accept the Terms of Service")
        render(view: "register")
        return
      }
      User user = ControllerHelper.findUser(session, request, response)
      user.profile = profile;
      user.guest = false
      user.save(flush: true);

      if (user.hasErrors()) {
        render(view: "register")
        return
      }
      else {
        session.user = user
        session.username = user.username

        redirect(controller: "home", view: "index")
        return
      }
    }
    else {
      render(view: "register")
    }
  }

}
