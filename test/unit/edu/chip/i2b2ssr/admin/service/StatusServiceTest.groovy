package edu.chip.i2b2ssr.admin.service

import grails.test.mixin.TestFor
import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.data.QuerySession
import grails.test.mixin.Mock
import edu.chip.i2b2ssr.admin.data.Status
import edu.chip.i2b2ssr.admin.data.Machine



@TestFor(StatusService)
@Mock([Status, Machine])
class StatusServiceTest {

    static def xml = """
        <runQueryResponse>
            <queryId>3796401183509203133</queryId>
            <instanceId>18779701026353076</instanceId>
            <userId>systemJobUser</userId>
            <groupId>machine-StagingNode1</groupId>
        <requestXml>
        </requestXml>
        <createDate>2012-11-13T13:19:46.933-05:00</createDate>
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
    </runQueryResponse>
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

}

