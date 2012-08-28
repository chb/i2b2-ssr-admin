package edu.chip.i2b2ssr.admin.jobs

import edu.chip.i2b2ssr.admin.service.AuthenticationService

/**
 * @author David Ortiz
 * @date 8/28/12
 * @link http://cbmi.med.harvard.edu
 * @link http://chip.org
 *       <p/>
 *       NOTICE: This software comes with NO guarantees whatsoever and is
 *       licensed as Lgpl Open Source
 * @link http://www.gnu.org/licenses/lgpl.html
 */

class QuerySessionCleanerJob {
  AuthenticationService authenticationService

  static triggers = {
    //one once per hour
    simple name: 'querySessionCleanerTrigger', startDelay: 60000, repeatInterval: 3600000
  }
  def group = "MyGroup"

  def execute(context) {
    log.info("Cleaning up query sessions")
    authenticationService.cleanupQuerySessions()
  }

}
