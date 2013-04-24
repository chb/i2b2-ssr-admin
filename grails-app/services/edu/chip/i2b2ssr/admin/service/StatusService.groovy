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
import org.springframework.transaction.annotation.Transactional
import net.shrine.client.JerseyShrineClient
import net.shrine.protocol.query.QueryDefinition
import net.shrine.protocol.query.Term

class StatusService {
    static transactional = false



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

                } else {
                    m.endpointStatus = Machine.UNREACHABLE
                }
                m.save()
            }
            catch (Exception e) {
                e.printStackTrace()
                log.fatal("Host: ${m?.realName} isn't reachable and/or SHRINE isn't responding on the the endpoing at ${m.url}")
            }
        }
    }

    def void runHeartBeat() {
        Long sessionId = null
        AuthenticationInfo auth
        List<Long> machineIds = new ArrayList<Long>()
        Long userId
        String shrineCellAddress
        Boolean wroteQuerySession = false
        /*
   TODO - FIXME - i2b2 makes a generic find-all-patients query difficult because there
   doesn't seem to be a good way in i2b2 to say, hey bro, give me all your patients, you
   need to specify a term which absolutely kills portability.  We'll have to make this a config
   option at some point :-/
  */
        def queryDef = new QueryDefinition("CARRA Registry v2.0",
                new Term("\\CARRA Registry v2.0\\"))
        /*
            The status happens in 3 steps
            1) Create a temporary QuerySession in it's own transaction
            2) Run status check for each machine in it's own transaction so
            3) Delete QuerySession in it's own transaction

         */
        User.withTransaction { status ->
            try {
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
                wroteQuerySession = true
                sessionId = tempSession.id
                Preference p = Preference.first()
                if (!sessionId) {
                    log.fatal("There was a previous error creating a temporary sesion, can't continue")
                }
                shrineCellAddress = p.shrineCell
                userId = u.id
                auth = new AuthenticationInfo("test", u?.userName,
                        new Credential(tempSession?.sessionId, true))
                machineIds.addAll(Machine.all*.id)
            }
            catch (Exception e) {
                e.printStackTrace()
                log.fatal("Error writing to database")
                status.setRollbackOnly();
            }
        }

        if (!wroteQuerySession) {
            throw new RuntimeException("Couldn't write query session, bombing out of status check")
        }

        //One transaction per machine so individual results can be logged
        for (Long id : machineIds) {
            Status.withTransaction { status ->

                Machine m = Machine.findById(id)
                if (m.endpointStatus == Machine.UNREACHABLE) {
                    log.info("Skipping heartbeat on ${m?.name} because the endpoint isn't available")

                } else {
                    def url = shrineCellAddress + "/rest/"
                    JerseyShrineClient client = new JerseyShrineClient(url, "machine-${m.name}", auth, true)
                    long start = System.currentTimeMillis();
                    RunQueryResponse r = client.runQuery("heartbeat", [ResultOutputType.PATIENT_COUNT_XML()] as Set, queryDef)
                    long numberOfPatients = r.singleNodeResult().setSize()
                    long end = System.currentTimeMillis()
                    Status s = new Status(numberOfPatients: numberOfPatients, responseTimeInMillis: (end - start))
                    s.machine = m
                    s.save(failOnError: true)


                }
            }

        }

        User.withTransaction { status ->
            QuerySession tempSession = QuerySession.findById(sessionId)
            tempSession.user.removeFromQuerySessions(tempSession)
            tempSession.delete(flush: true)
        }


    }

    def void cleanupOldStatus() {
        Calendar c1 = Calendar.getInstance()
        c1.add(Calendar.DAY_OF_YEAR, -30)
        for (status in Status.findAllByTimeStampLessThan(c1.getTime())) {
            status.delete(flush: true)
        }
    }
}
