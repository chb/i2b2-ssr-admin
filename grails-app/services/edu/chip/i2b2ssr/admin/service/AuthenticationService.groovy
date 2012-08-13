package edu.chip.i2b2ssr.admin.service

import edu.chip.i2b2ssr.admin.data.QuerySession
import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.service.backend.IAuthenticator

import static java.util.UUID.randomUUID

class AuthenticationService {
    def grailsApplication
    static transactional = true

    IAuthenticator authenticator

    /**
     *
     * @param username
     * @param password
     * @return returns a User object if authentication is successful and null otherwise
     */
    def User authenticate(String username, String password) {
        User user = User.findByUserName(username)

        if (authenticator.authenticate(username, password)) {
            //If the user is in the LDAP and there's no users in the system
            //just create a new one and make them the admin, it's no ideal but eh...
            if (user == null && User.count == 0) {
                User newUser = new User(userName: username, isAdmin: true)
                newUser.save()
                user = newUser
            }
            return user
        }
        return null
    }

    def QuerySession authenticateWithSession(String username, String password) {
        User u = authenticate(username, password)
        QuerySession q;
        if (u) {
            q = new QuerySession(sessionId: randomUUID())
            u.addToQuerySessions(q)
            u.save()
        }
        return q;
    }

    /**
     * This method will clean up all the old sessions that are older
     * than timeout
     *
     * @param timeout - timeout in minutes
     */
    def void cleanupQuerySessions(Integer timeout) {
        Calendar c1 = Calendar.getInstance()
        c1.add(Calendar.MINUTE, (timeout * -1))
        for(session in QuerySession.findAllByCreatedLessThan(c1.getTime())){
            session.delete(flush: true)
        }
    }

    def void cleanupQuerySessions() {
        cleanupQuerySessions(grailsApplication.config.i2b2ssr.querySessionTimeout)

    }

  def boolean checkSessionKey(String username, String sessionKey) {

    def time = Calendar.getInstance().add(Calendar.MINUTE, -30)


    def query = QuerySession.where {
      user.userName == username && sessionKey == sessionKey && created >= time
    }
    return query.count() > 0

  }

}
