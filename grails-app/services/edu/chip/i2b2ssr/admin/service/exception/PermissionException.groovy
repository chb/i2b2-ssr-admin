package edu.chip.i2b2ssr.admin.service.exception

/**
 * @author David Ortiz
 * @date 7/31/12
 * @link http://cbmi.med.harvard.edu
 * @link http://chip.org
 *       <p/>
 *       NOTICE: This software comes with NO guarantees whatsoever and is
 *       licensed as Lgpl Open Source
 * @link http://www.gnu.org/licenses/lgpl.html
 */

class PermissionException extends RuntimeException{

  PermissionException(String s){
    super(s)
  }
}
