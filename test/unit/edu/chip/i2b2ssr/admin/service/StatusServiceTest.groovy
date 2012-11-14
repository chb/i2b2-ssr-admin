package edu.chip.i2b2ssr.admin.service

import grails.test.mixin.TestFor



@TestFor(StatusService)
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


    def testParseResponse() {
        StatusService testInstance = new StatusService()
        long setSize  = testInstance.setSizeFromResponseXML(xml)
        assertEquals(121L, setSize)


    }

}

