import edu.chip.i2b2ssr.admin.data.Preference

class BootStrap {

    def init = { servletContext ->
      if(Preference.all.size() == 0){
        //if there's no preferences in the DB add some new ones
        new Preference().save(failOnError: true)
      }
    }
    def destroy = {
    }
}
