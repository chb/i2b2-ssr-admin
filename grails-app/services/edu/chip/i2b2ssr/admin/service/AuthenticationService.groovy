package edu.chip.i2b2ssr.admin.service

import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.data.QuerySession
import static java.util.UUID.randomUUID
import edu.chip.i2b2ssr.admin.service.backend.IAuthenticator

class AuthenticationService {

  IAuthenticator authenticator

  /**
   *
   * @param username
   * @param password
   * @return returns a User object if authentication is successful and null otherwise
   */
  def User authenticate(String username, String password) {
    User user = User.findByUserName(username)

    if(authenticator.authenticate(username, password)) {
      //If the user is in the LDAP and there's no users in the system
      //just create a new one and make them the admin, it's no ideal but eh...
      if(user == null && User.count == 0) {
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
    if(u) {
      q = new QuerySession(sessionId: randomUUID())
      u.addToQuerySessions(q)
      u.save()
    }
    return q;
  }

  def boolean checkSessionKey(String username, String sessionKey) {

    def time = Calendar.getInstance().add(Calendar.MINUTE, -30)


    def query = QuerySession.where {
      user.userName == username && sessionKey == sessionKey && created >= time
    }
    return query.count() > 0

  }

}
