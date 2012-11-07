package edu.chip.i2b2ssr.admin.service.backend

import edu.chip.i2b2ssr.admin.service.exception.PermissionException
import org.apache.commons.logging.LogFactory
import com.unboundid.ldap.sdk.*

/**
 * @author Dave Ortiz
 * @date 7/31/12
 * @link http://cbmi.med.harvard.edu
 * @link http://chip.org
 *       <p/>
 *       NOTICE: This software comes with NO guarantees whatsoever and is
 *       licensed as Lgpl Open Source
 * @link http://www.gnu.org/licenses/lgpl.html
 */

/**
 * This class implements the IAuthenticator interface using a remote LDAP
 */
class LDAPAuthenticator implements IAuthenticator {

  private String baseDn;
  private String userIdentifier
  private LDAPConnectionPool pool

  private static final log = LogFactory.getLog(this)

  def LDAPAuthenticator(String ldapAddress, int ldapPort, String ldapBindDn,
          String ldapBindPassword, String baseDn, String userIdentifier) throws LDAPException {
    this.baseDn = baseDn
    this.userIdentifier = userIdentifier
    LDAPConnection con = new LDAPConnection(ldapAddress, ldapPort, ldapBindDn, ldapBindPassword)
    pool = new LDAPConnectionPool(con, 10, 20);
  }


  def LDAPAuthenticator(String ldapAddress, int ldapPort, String baseDn, String userIdentifier) throws LDAPException {
    this.baseDn = baseDn;
    this.userIdentifier = userIdentifier;
    LDAPConnection con = new LDAPConnection(ldapAddress, ldapPort);
    pool = new LDAPConnectionPool(con, 10, 20);
  }


  def boolean authenticate(String username, String password) {
    def con = pool.getConnection()

    try {

      SearchResult sr = con.search(baseDn, SearchScope.SUB, (userIdentifier + "=" + username));
      if(sr.getSearchEntries().size() < 1) {
        throw new PermissionException("No such user");
      }

      if(sr.getEntryCount() > 0) {
        String dn = sr.getSearchEntries().get(0).getDN();
        con.bind()
        pool.releaseDefunctConnection(con)
        return true

      }
      else {
        return false
      }

    } catch(LDAPException e) {
      log.error("Recieved LDAP exception: " + e.message);
      return false
    }
    finally {
      if(con) {
        pool.releaseConnection(con)
      }

    }


  }
}
