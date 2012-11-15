
class SecurityFilters {

  def filters = {

    all(controller: '(user|machine|study|permission|status|console)', action: '*') {
      before = {
        if(actionName == "login" || actionName == "auth" || (session?.user?.isAdmin == true)) {
          return true
        }
        else if(!session.user) {
          redirect(controller: "user", action: "login")
          return false
        }
        else{
            return true
        }
      }
    }

  }
}
