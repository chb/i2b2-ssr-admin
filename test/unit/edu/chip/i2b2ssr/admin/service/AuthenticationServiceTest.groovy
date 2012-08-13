package edu.chip.i2b2ssr.admin.service

import grails.test.mixin.TestFor
import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.data.QuerySession
import grails.test.mixin.Mock
import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.service.backend.IAuthenticator

@TestFor(AuthenticationService)
@Mock([QuerySession, User])
class AuthenticationServiceTest {

    /**
     * Test our method to cleanup background sessions, this
     * is something that is going to run periodically in the background
     * on the server
     */
    def testCleanupOldQuerySessions() {
        Integer timeout = grailsApplication.config.i2b2ssr.querySessionTimeout
        Calendar c1 = Calendar.getInstance()
        Calendar c2 = Calendar.getInstance()
        Calendar c3 = Calendar.getInstance()

        c1.add(Calendar.MINUTE, (timeout * -1) + 10)
        c2.add(Calendar.MINUTE, (timeout * -1) - 10)
        c3.add(Calendar.MINUTE, (timeout * -1) - 20)

        User u1 = new User(userName: "testUser")
        u1.addToQuerySessions(new QuerySession(created: c1.getTime()))
        u1.addToQuerySessions(new QuerySession(created: c2.getTime()))
        u1.addToQuerySessions(new QuerySession(created: c3.getTime()))

        u1.save(failOnError: true)
        def newService = new AuthenticationService()
        newService.cleanupQuerySessions(timeout)
        assertEquals(1, QuerySession.all.size())
    }

    def testAuthentication(){
        def newService = new AuthenticationService()
        def alwaysAuthenticate =  {String userName, String password -> true} as IAuthenticator
        def neverAuthenticate = {String userName, String password -> false} as IAuthenticator

        newService.authenticator =  alwaysAuthenticate
        assertEquals(true ,newService.authenticate("test", "test") != null)

        //test that first user is created
        assertEquals(true, User.findByUserName("test") != null)
        assertEquals(true, User.findByUserName("test").isAdmin)

        newService.authenticator = neverAuthenticate
        assertEquals(null, newService.authenticate("bad","bad"))

    }


}
