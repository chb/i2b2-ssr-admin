package edu.chip.i2b2ssr.admin.jobs

import edu.chip.i2b2ssr.admin.data.Machine
import edu.chip.i2b2ssr.admin.service.StatusService

/**
 * @author David Ortiz
 * @date 8/28/12
 * @link http://cbmi.med.harvard.edu
 * @link http://chip.org
 *       <p/>
 *       NOTICE: This software comes with NO guarantees whatsoever and is
 *       licensed as Lgpl Open Source
 * @link http://www.gnu.org/licenses/lgpl.html
 *
 * This is a background task for keeping track of the status of the cluster
 *
 */
class StatusJob {
  static triggers = {
    //run once per minute until Networking yells at me
    simple name: 'mySimpleTrigger', startDelay: 60000, repeatInterval: 60000
  }
  def group = "MyGroup"

  def StatusService statusService

  def execute() {
    log.info("Running status check on cluster")
    statusService.checkEndpointStatus()

  }


}
