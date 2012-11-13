package edu.chip.i2b2ssr.admin.service

import edu.chip.i2b2ssr.admin.data.QuerySession
import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.service.backend.IAuthenticator

import static java.util.UUID.randomUUID
import edu.chip.i2b2ssr.admin.data.Permission
import edu.chip.i2b2ssr.admin.service.exception.PermissionException

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
            if (user == null && noAdmin() == true) {
                User newUser = new User(userName: username, isAdmin: true)
                newUser.save(failOnError: true, flush: true)
                user = newUser

            }
            else if(user && user.isSystemUser){
              throw new PermissionException("Attempting to login as system user")
            }
          return user
        }
        return null
    }

    private Boolean noAdmin() {
      User.where{
        isAdmin == true
      }.count() == 0
    }

    def QuerySession authenticateWithSession(String username, String password) {
        User u = authenticate(username, password)
        QuerySession q;
        if (u) {
            u.lock()
            q = new QuerySession(sessionId: randomUUID().toString())

            u.addToQuerySessions(q)
            q.save(failOnError: true, flush: true)
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
        for (session in QuerySession.findAllByCreatedLessThan(c1.getTime())) {
            session.delete(flush: true)
        }
    }

    def void cleanupQuerySessions() {
        cleanupQuerySessions(grailsApplication.config.i2b2ssr.querySessionTimeout)

    }

    def boolean checkSessionKey(String username, String sessionKey) {
        def time = Calendar.getInstance().add(Calendar.MINUTE, (grailsApplication.config.i2b2ssr.querySessionTimeout * -1))
        def result = QuerySession.where {
            user.userName == username && sessionId == sessionKey
        }.get()
        if (!result) {
            return false;
        }
        else {
            return result.created >= time
        }
    }

    def Boolean isSystemUser(String userName) {
        def user = User.findByUserName(userName)
        def result = user != null && user.isSystemUser
        return result
    }

    def Permission findPermission(String username, String projectName) {
        def permission = Permission.where {
            user.userName == username && study.studyName == projectName

         }.get()

        return permission
    }

}
