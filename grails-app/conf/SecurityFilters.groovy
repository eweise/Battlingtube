import com.bt.domain.User

/**
 * Created by IntelliJ IDEA.
 * User: eric
 * Date: Mar 13, 2008
 * Time: 9:33:26 PM
 * To change this template use File | Settings | File Templates.
 */
class SecurityFilters {
  def filters = {
    loginCheck(controller: '*', action: '*') {
      before = {
        String action = actionName == null ? "" : actionName;
        String controller = controllerName;

        User user = session.user
        if (!isAdminUser(user) && controller.startsWith("test")) {
          return false
        }

        if ((!user || user.isGuest()) && restrictedPage(controller, action)) {
          saveOriginalParamInSession(controller, action, params, session)
          redirect(controller: "user", action: 'login')
          return false
        }
      }
    }
  }

  private boolean restrictedPage(String controller, String action) {
    return action.contains("create") ||
            action.contains("edit") ||
            (controller.startsWith("activity") && !action.contains("voted"))
  }

  private def saveOriginalParamInSession(def controllerName, def actionName, def params, def session) {
    def originalRequestParams =
    [controller: controllerName,
            action: actionName]

    originalRequestParams.putAll(params)

    session.originalRequestParams =
      originalRequestParams
  }

  def isAdminUser(User user) {
    return user?.isAdmin()
  }

}