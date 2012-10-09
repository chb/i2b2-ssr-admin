package edu.chip.i2b2ssr.admin.jobs

import edu.chip.i2b2ssr.admin.data.Machine

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

  def execute() {
    log.info("Running status check on cluster")
    for(Machine m in Machine.all) {
      //100 Millis is ok on our network
      try {
        log.info("Checking host " + m.realName)
        if(InetAddress.getByName(m.url.getHost()).isReachable(100)) {
          m.setStatus(Machine.MACHINE_AVAILABLE)
          if(m.url.getContent()) {
            m.setStatus(Machine.SHRINE_OK)
          }
        }
        else {
          m.setStatus(Machine.MACHINE_BAD)
        }
        m.save()
      }

      catch(IOException e) {
        log.info("Caught IOException")
      }

    }

  }


}
