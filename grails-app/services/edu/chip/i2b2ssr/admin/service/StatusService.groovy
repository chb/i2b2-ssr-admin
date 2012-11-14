package edu.chip.i2b2ssr.admin.service

import edu.chip.i2b2ssr.admin.data.Machine
import edu.chip.i2b2ssr.admin.data.QuerySession
import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.data.Preference
import net.shrine.protocol.AuthenticationInfo
import net.shrine.protocol.Credential
import net.shrine.protocol.ResultOutputType
import net.shrine.protocol.RunQueryResponse
import edu.chip.i2b2ssr.admin.data.Status
import net.shrine.util.JerseyShrineClient
import org.springframework.transaction.annotation.Transactional

class StatusService {
    static transactional = false

    private String heartbeatQueryPanel = """
        <ns4:query_definition xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                        xmlns:ns8="http://www.i2b2.org/xsd/hive/msg/result/1.1/"
                        xmlns:ns6="http://www.i2b2.org/xsd/cell/crc/psm/analysisdefinition/1.1/"
                        xmlns:ns5="http://www.i2b2.org/xsd/cell/crc/psm/querydefinition/1.1/"
                        xmlns:imsg="http://www.i2b2.org/xsd/hive/msg/1.1/"
                        xmlns:ns3="http://www.i2b2.org/xsd/hive/plugin/"
                        xmlns:ns4="http://www.i2b2.org/xsd/cell/crc/psm/1.1/"
                        xmlns:ns2="http://www.i2b2.org/xsd/hive/pdo/1.1/">
                  <query_name>CARRAnet </query_name>
                  <query_description>Status Query</query_description>
                  <query_timing>ANY</query_timing>
                  <specificity_scale>0</specificity_scale>
                  <panel name="Panel_31">
                      <panel_number>1</panel_number>
                      <panel_accuracy_scale>0</panel_accuracy_scale>
                      <invert>0</invert>
                      <total_item_occurrences>1</total_item_occurrences>
                      <item>
                          <hlevel>1</hlevel>
                          <item_name>root</item_name>
                          <item_key>\\</item_key>
                          <dim_tablename>concept_dimension</dim_tablename>
                          <dim_columnname>concept_path</dim_columnname>
                          <dim_dimcode>\\</dim_dimcode>
                          <dim_columndatatype>T</dim_columndatatype>
                          <facttablecolumn>concept_cd</facttablecolumn>
                          <item_is_synonym>false</item_is_synonym>
                      </item>
                  </panel>
              </ns4:query_definition>
  """

    @Transactional
    def checkEndpointStatus() {
        log.info("Running status check on cluster")
        for (Machine m in Machine.all) {
            //100 Millis is ok on our network
            try {
                log.info("Checking host " + m.realName)
                if (InetAddress.getByName(m.url.getHost()).isReachable(100) &&
                        m.url.getContent()) {
                    m.endpointStatus = Machine.REACHABLE

                }
                else {
                    m.endpointStatus = Machine.UNREACHABLE
                }
                m.save()
            }
            catch (Exception e) {
                log.fatal("Error checking machine: " + m?.realName)
            }
        }
    }

    def void runHeartBeat() {

        String sessionId = null

        //create a new temporary session, to be deleted immediately after completing the query
        User.withTransaction { status ->
            log.info("Running status check on cluster")

            User u = User.backgroundJobUser
            if (u == null) {
                log.fatal("Can't find i2b2 background job user, it should've been" +
                        "created by BootStrap.groovy")
                return
            }
            QuerySession tempSession = new QuerySession(sessionId: UUID.randomUUID().toString())
            u.addToQuerySessions(tempSession)
            u.save(failOnError: true, flush: true)
            sessionId = tempSession.sessionId

        }


        Preference.withTransaction { status ->
            Preference p = Preference.first()

            if (!sessionId) {
                log.fatal("There was a previous error creating a temporary sesion, can't continue")
            }

            QuerySession tempSession = QuerySession.findBySessionId(sessionId)
            User u = tempSession?.user
            AuthenticationInfo auth = new AuthenticationInfo("test", u?.userName,
                    new Credential(tempSession?.sessionId, true))

            for (Machine m : Machine.all) {
                def url = p.shrineCell + "/rest/"
                JerseyShrineClient client = new JerseyShrineClient(url, "machine-${m.name}", auth, true)
                long start = System.currentTimeMillis();
                RunQueryResponse r = client.runQuery("heartbeat", [ResultOutputType.PATIENT_COUNT_XML] as Set, heartbeatQueryPanel)
                long numberOfPatients = setSizeFromResponseXML(r.toXml().toString())


                long end = System.currentTimeMillis()
                m.addToStatuses(new Status(numberOfPatients: numberOfPatients, responseTimeInMillis: (end - start)))
                m.save(failOnError: true)
            }
            tempSession.delete(flush: true)
        }
    }

    protected def Long setSizeFromResponseXML(String responseXML) {
        return 1L
    }


}
