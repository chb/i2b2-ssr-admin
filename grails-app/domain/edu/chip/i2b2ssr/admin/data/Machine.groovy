package edu.chip.i2b2ssr.admin.data

class Machine {

  static def REACHABLE = "Reachable"
  static def UNREACHABLE = "Unreachable"

  String name
  String certificate
  String realName


  URL url
  String endpointStatus = UNREACHABLE
  SortedSet<Status> statuses
  static hasMany = [studies: Study, statuses: Status]
  static belongsTo = Study
  static constraints = {
    certificate nullable: true
    name unique: true
    endpointStatus(nullable: true, is: ([REACHABLE,
            UNREACHABLE]))
  }

  def Status latestStatus() {
    if(statuses?.size() > 0) {
      statuses.last()
    }
    else {
      null
    }
  }


}
