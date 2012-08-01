import edu.chip.i2b2ssr.admin.data.Preference
import grails.util.Environment
import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.service.backend.TestAuthenticator

// Place your Spring DSL code here
beans = {
  switch(Environment.current){
    case Environment.TEST:
      authService(AuthenticationService){
          authenticator = new TestAuthenticator()
        }
      break
    case Environment.DEVELOPMENT:
      authService(AuthenticationService){
         authenticator = new TestAuthenticator()
       }
     break
    case Environment.PRODUCTION:
       //setup Real LDAP
     break

  }

}
