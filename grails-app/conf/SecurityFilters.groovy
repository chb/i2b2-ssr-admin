
class SecurityFilterFilters {

  def filters = {

    all(controller: '(user|machine|study|permission|status)', action: '*') {
      before = {
        if(actionName == "login" || actionName == "auth" || (session?.user?.isAdmin == true)) {
          return true
        }
        if(!session.user) {
          redirect(controller: "user", action: "login")
          return false
        }
      }
    }

  }
}
