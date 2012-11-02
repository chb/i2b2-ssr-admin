package edu.chip.i2b2ssr.admin.service

import edu.chip.i2b2ssr.admin.data.Machine
import edu.chip.i2b2ssr.admin.data.QuerySession
import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.data.Preference
import org.apache.jasper.tagplugins.jstl.core.Url

class StatusService {
  static transactional = true


  def checkEndpointStatus() {
    log.info("Running status check on cluster")
    for(Machine m in Machine.all) {
      //100 Millis is ok on our network
      try {
        log.info("Checking host " + m.realName)
        if(InetAddress.getByName(m.url.getHost()).isReachable(100) &&
                m.url.getContent()) {
          m.endpointStatus = Machine.REACHABLE

        }
        else {
          m.endpointStatus = Machine.UNREACHABLE
        }
        m.save()
      }
      catch(Exception e) {
        log.fatal("Error checking machine: " + m?.realName)
      }
    }
  }

  def void runHeartBeat() {
    log.info("Running status check on cluster")

    User u = User.backgroundJobUser
    if(u == null) {
      log.fatal("Can't find i2b2 background job user, it should've been" +
              "created by BootStrap.groovy")
    }

    assert u.isSystemUser == true

    //create a new temporary session, to be deleted immediately after completing the query
    def tempSession = new QuerySession(user: u)
    tempSession.save(failOnError: true, flush: true)
    Preference p = Preference.first()
    if(p.heartBeatStudy?.studyName) {
      def url = new URL(Preference.first().shrineCell +
              "/statusCheck?username=\"${u.userName}\"&password=\"${tempSession.sessionId}\"&project=\"${p.heartBeatStudy.studyName}\"")
      String response = url.getContent().toString()
    }
    tempSession.delete(flush: true)
  }


}
