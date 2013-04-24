package edu.chip.i2b2ssr.admin.service

import edu.chip.i2b2ssr.admin.TestUtil
import edu.chip.i2b2ssr.admin.data.*
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import groovy.mock.interceptor.MockFor
import net.shrine.client.JerseyShrineClient
import net.shrine.client.ShrineClient
import net.shrine.protocol.AggregatedRunQueryResponse
import net.shrine.protocol.AuthenticationInfo
import net.shrine.protocol.ResultOutputType
import net.shrine.protocol.RunQueryResponse
import net.shrine.protocol.query.QueryDefinition

@TestFor(StatusService)
@Mock([Status, Machine, User, QuerySession, Preference])
class StatusServiceTest {

    static def xml = """
<aggregatedRunQueryResponse>
    <queryId>1</queryId>
    <instanceId>2</instanceId>
    <userId>user</userId>
    <groupId>group</groupId>
    <requestXml>
        <queryDefinition>
            <name>queryName</name>
            <expr>
                <term>\\\\i2b2\\i2b2\\Demographics\\Age\\0-9 years old\\</term>
            </expr>
        </queryDefinition>
    </requestXml>
    <createDate>2002-05-30T09:30:10-06:00</createDate>
    <queryResults>
        <queryResult>
            <resultId>0</resultId>
            <instanceId>18779701026353076</instanceId>
            <resultType>PATIENT_COUNT_XML</resultType>
            <setSize>121</setSize>
            <startDate>2012-11-13T13:19:46.933-05:00</startDate>
            <endDate>2012-11-13T13:19:46.933-05:00</endDate>
            <description>TOTAL COUNT</description>
            <status>FINISHED</status>
        </queryResult>
    </queryResults>
</aggregatedRunQueryResponse>
    """

    /**
     * Test that we the patient size out of the response
     *
     */
    def testParseResponse() {
        StatusService testInstance = new StatusService()
        long setSize = testInstance.setSizeFromResponseXML(xml)
        assertEquals(121L, setSize)


    }

    /**
     * Test that cleanup sweeps up Status messages that our older than 30 days
     *
     */
    def testCleanup() {
        Calendar c1 = Calendar.getInstance()
        Calendar c2 = Calendar.getInstance()
        Calendar c3 = Calendar.getInstance()
        Calendar c4 = Calendar.getInstance()


        c1.add(Calendar.DAY_OF_YEAR, -31)
        c2.add(Calendar.DAY_OF_YEAR, -29)
        c3.add(Calendar.DAY_OF_YEAR, -17)


        Machine m1 = new Machine(name: "Test1", realName: "TEST MACHINE 1", url: "https://testurl.com")
        Status s1 = new Status(numberOfPatients: 1, responseTimeInMillis: 1, timeStamp: c1.getTime())
        Status s2 = new Status(numberOfPatients: 2, responseTimeInMillis: 2, timeStamp: c2.getTime())
        Status s3 = new Status(numberOfPatients: 3, responseTimeInMillis: 3, timeStamp: c3.getTime())

        m1.addToStatuses(s1)
        m1.addToStatuses(s2)
        m1.addToStatuses(s3)

        m1.save(failOnError: true)
        def newService = new StatusService()

        newService.cleanupOldStatus()

        assertEquals(2, Status.all.size())
        assertNotNull(Status.findByNumberOfPatients(2))
        assertNotNull(Status.findByNumberOfPatients(3))


    }



    /*
        A simple test to go end-to-end with the SHRINE JerseyShrineClient,
        most of the complexity lies in making sure we're giving the Scala Serializers
        something they won't die on and making sure our classpath is setup properly.
     */
    def testStatusQuery() {
        User u = new User(userName: User.SYSTEM_USER, isSystemUser: true)
        Preference p = new Preference(i2b2OntCell: "http://test.com", shrineCell: "http://test.org")
        p.save(failOnError: true)

        u.save(failOnError: true)

        Machine testM1 = new Machine(name: "TEST", realName: "TESTMACHINE",
                url: new URL("http://test.com"), endpointStatus: Machine.REACHABLE)
        testM1.save(failOnError: true)
        StatusService testService = new StatusService()

        RunQueryResponse resp0 = RunQueryResponse.fromXml(TestUtil.loadScalaXml(xml))
         def mock = new MockFor(JerseyShrineClient)
        mock.demand.runQuery(1) {
            String arg1, Set<ResultOutputType> arg2, QueryDefinition d -> return resp0
        }

        mock.use {
            testService.runHeartBeat()
        }
        assertEquals(testM1.latestStatus().numberOfPatients, 121 )
    }


}

