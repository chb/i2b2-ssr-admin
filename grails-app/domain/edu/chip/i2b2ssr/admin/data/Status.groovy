package edu.chip.i2b2ssr.admin.data

/**
 * @author David Ortiz
 * @date 10/18/12
 * @link http://cbmi.med.harvard.edu
 * @link http://chip.org
 *       <p/>
 *       NOTICE: This software comes with NO guarantees whatsoever and is
 *       licensed as Lgpl Open Source
 * @link http://www.gnu.org/licenses/lgpl.html
 */

class Status implements Comparable<Status> {
  Machine machine
  Date timeStamp = new Date()
  Long numberOfPatients
  Long responseTimeInMillis

  static belongsTo = [Machine]



  static constraints = {

  }

  public int compareTo(Status other) {
      return timeStamp <=> other?.timeStamp // <=> is the compareTo operator in groovy
  }


}
