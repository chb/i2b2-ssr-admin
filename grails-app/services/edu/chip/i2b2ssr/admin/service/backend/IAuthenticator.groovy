package edu.chip.i2b2ssr.admin.service.backend

import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.data.QuerySession

/**
 * This interface
 *
 */
public interface IAuthenticator {
  boolean authenticate(String username, String password)


}