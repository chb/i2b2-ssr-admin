import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.service.backend.IAuthenticator
import grails.util.Environment
import edu.chip.i2b2ssr.admin.service.backend.LDAPAuthenticator

// Place your Spring DSL code here
beans = {
    switch (Environment.current) {
        case Environment.DEVELOPMENT:
            authService(AuthenticationService) {
                authenticator = { String userName, String password -> true } as IAuthenticator


            }
            break
        case Environment.TEST:
            authService(AuthenticationService) {
                authenticator = { String userName, String password -> true } as IAuthenticator
                grailsApplication = grailsApplication
            }
            break
        case Environment.PRODUCTION:
            authService(AuthenticationService) {

                //use this to test the test LDAP on carra-nexus
                //authenticator = new LDAPAuthenticator("carra-nexus", 389, "ou=people,dc=carranet,dc=org", "uid")


                authenticator = new LDAPAuthenticator(grailsApplication.config.ldap.host,
                                                    new Integer(grailsApplication.config.ldap.port),
                        grailsApplication.config.ldap.bindDn, grailsApplication.config.ldap.password,
                        grailsApplication.config.ldap.basedn, grailsApplication.config.ldap.useridentifier)
                grailsApplication = grailsApplication
            }
            break

    }

}
