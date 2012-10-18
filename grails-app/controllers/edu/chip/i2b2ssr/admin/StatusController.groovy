package edu.chip.i2b2ssr.admin

import edu.chip.i2b2ssr.admin.data.Machine

/**
 * @author David Ortiz
 * @date 8/27/12
 * @link http://cbmi.med.harvard.edu
 * @link http://chip.org
 *       <p/>
 *       NOTICE: This software comes with NO guarantees whatsoever and is
 *       licensed as Lgpl Open Source
 * @link http://www.gnu.org/licenses/lgpl.html
 */

class StatusController {
  String menuName = "Cluster Status"


  def index = {

    [machines: Machine.all]

  }

}
