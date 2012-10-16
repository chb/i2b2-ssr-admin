import edu.chip.i2b2ssr.admin.data.Preference
import edu.chip.i2b2ssr.admin.data.User

class BootStrap {

    def init = { servletContext ->
        if (Preference.all.size() == 0) {
            //if there's no preferences in the DB add some new ones       78u
            new Preference().save(failOnError: true)
        }
        //check if there's a backend system user, if not make one
        def u = User.findByUserName(User.SYSTEM_USER)
        if(u == null){
            User newUser = new User(userName: User.SYSTEM_USER, isSystemUser: true)
            newUser.save(failOnError: true, flush: true)

        }

    }
    def destroy = {
    }
}
