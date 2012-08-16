import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.service.backend.IAuthenticator
import grails.util.Environment
import edu.chip.i2b2ssr.admin.service.backend.LDAPAuthenticator

// Place your Spring DSL code here
beans = {
  //Setup background QueryServiceSweeper
  doQuerySessionTimerTask(org.springframework.scheduling.timer.MethodInvokingTimerTaskFactoryBean) {
    targetObject = ref("authenticationService")
    targetMethod = 'cleanupQuerySessions'
  }

  doQuerySessionScheduledTimerTask(org.springframework.scheduling.timer.ScheduledTimerTask) {
    delay = 60000
    period = 60000
    timerTask = ref('doQuerySessionTimerTask')
  }

  timerFactory(org.springframework.scheduling.timer.TimerFactoryBean) {
    scheduledTimerTasks = [ref('doQuerySessionScheduledTimerTask')]
  }

  switch(Environment.current) {
    case Environment.DEVELOPMENT:
      authService(AuthenticationService) {
        authenticator = {String userName, String password -> true} as IAuthenticator
      }
      break
    case Environment.TEST:
      authService(AuthenticationService) {
        authenticator = {String userName, String password -> true} as IAuthenticator
      }
      break
    case Environment.PRODUCTION:
      authService(AuthenticationService) {
        authenticator = new LDAPAuthenticator("carra-nexus", 389, "ou=people,dc=carranet,dc=org", "uid")
      }
      break

  }

}
