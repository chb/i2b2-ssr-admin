import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.service.backend.IAuthenticator
import grails.util.Environment
import edu.chip.i2b2ssr.admin.service.backend.LDAPAuthenticator

// Place your Spring DSL code here
beans = {
  switch(Environment.current) {
    case Environment.DEVELOPMENT:
      authService(AuthenticationService) {
        authenticator = {String userName, String password -> true} as IAuthenticator
      }
      break
    case Environment.TEST:
      authService(AuthenticationService) {
        authenticator = {String userName, String password -> true} as IAuthenticator
        grailsApplication = grailsApplication
      }
      break
    case Environment.PRODUCTION:
      authService(AuthenticationService) {
        //authenticator = new LDAPAuthenticator("carra-nexus", 389, "ou=people,dc=carranet,dc=org", "uid")

        authenticator = new LDAPAuthenticator("ldap.chboston.org", 389,
                "CN=CarraNetSvc,OU=Special Accounts,DC=CHBDir,DC=Org", "D87kdRs1",
                "ou=users,dc=chbdir,dc=org", "cn")
        grailsApplication = grailsApplication
        }
      break

  }

}
