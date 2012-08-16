package edu.chip.i2b2ssr.admin



import grails.test.mixin.*
import org.junit.*
import edu.chip.i2b2ssr.admin.service.AuthenticationService
import edu.chip.i2b2ssr.admin.data.QuerySession
import edu.chip.i2b2ssr.admin.data.Preference
import edu.chip.i2b2ssr.admin.data.User
import edu.chip.i2b2ssr.admin.data.Permission
import edu.chip.i2b2ssr.admin.data.Study

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(I2b2PmController)
@Mock([AuthenticationService,Preference, User, QuerySession, Permission, Study])
class I2b2PmControllerTests {

  String pmAuthString = """
         <i2b2:request xmlns:i2b2="http://www.i2b2.org/xsd/hive/msg/1.1/"
                  xmlns:pm="http://www.i2b2.org/xsd/cell/pm/1.1/">
      <message_header>

        <i2b2_version_compatible>1.1</i2b2_version_compatible>
        <hl7_version_compatible>2.4</hl7_version_compatible>
        <sending_application>
          <application_name>i2b2 Project Management</application_name>
          <application_version>1.1</application_version>
        </sending_application>
        <sending_facility>
          <facility_name>i2b2 Hive</facility_name>
        </sending_facility>
        <receiving_application>
          <application_name>Project Management Cell</application_name>
          <application_version>1.1</application_version>
        </receiving_application>
        <receiving_facility>
          <facility_name>i2b2 Hive</facility_name>
        </receiving_facility>
        <datetime_of_message>2010-04-26T15:08:43-04:00</datetime_of_message>
        <security>
          <domain>HarvardDemo</domain>
          <username>admin</username>
          <password>admin</password>
        </security>
        <message_control_id>
          <message_num>bjh550i6kJlq0BXkhJ397</message_num>
          <instance_num>0</instance_num>
        </message_control_id>
        <processing_id>
          <processing_id>P</processing_id>
          <processing_mode>I</processing_mode>
        </processing_id>
        <accept_acknowledgement_type>AL</accept_acknowledgement_type>
        <application_acknowledgement_type>AL</application_acknowledgement_type>
        <country_code>US</country_code>
        <project_id>undefined</project_id>
      </message_header>
      <request_header>
        <result_waittime_ms>180000</result_waittime_ms>
      </request_header>
      <message_body>
        <pm:get_user_configuration>
          <project>undefined</project>
        </pm:get_user_configuration>
      </message_body>
    </i2b2:request>
  """

  @SuppressWarnings("unchecked")
  void testPMAuthentication() {
    def authServiceMock = mockFor(AuthenticationService)
    //Add a default preference object
    Preference p = new Preference(i2b2OntCell: "http://testurl.com", shrineCell: "http://testcellbaby").save()
    QuerySession q =  new QuerySession(sessionId: "TEST")
    User u = new User(userName: "admin")
    u.addToQuerySessions(q)
    u.save()

    Study s = new Study(studyName: "TestStudy").save()
    u.addToPermissions(study: s, allowPdo: true, allowSiteIdentify: true)
    u.save()

    authServiceMock.demand.authenticateWithSession {String userName, String Password ->
      return q
    }
    controller.authService = authServiceMock.createMock()

    request.method = "POST"
    request.content = pmAuthString.bytes
    controller.getServices()

    assert view == "/i2b2Pm/response.xml"
    assert model.session == q
    assert model.user == u
    assert model.preference == p
  }


}
