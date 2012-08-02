import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.service.backend.IAuthenticator
import grails.util.Environment

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

    switch (Environment.current) {

        case Environment.TEST:
            authService(AuthenticationService) {
                authenticator = {String userName, String password -> true} as IAuthenticator
            }
            break
        case Environment.DEVELOPMENT:
            authService(AuthenticationService) {
                authenticator = {String userName, String password -> true} as IAuthenticator
            }
            break
        case Environment.PRODUCTION:
            //setup Real LDAP
            break

    }

}
