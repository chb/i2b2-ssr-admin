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
          String ldapBindPassword, String baseDn, String userIdentifier)
  throws LDAPException {

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
    try {

      SearchResult sr = pool.search(basedn,
              SearchScope.SUB, (userIdentifier + "=" + username));

      if(sr.getSearchEntries().size() < 1) {
        throw new PermissionException("No such user");
      }
      String dn = sr.getSearchEntries().get(0).getDN();

      DN userDN = new DN(dn);

      if(r.getEntryCount() > 0 && ldap.bind(dn, pass) != null) {
        //If this doesn't throw an Exception, we're A-OK
        pool.bind(dn, pass);
        return true

      }
      else {
        return false
      }

    } catch(LDAPException e) {

      log.error("Recieved LDAP exception");
      return false
    }

  }
}
