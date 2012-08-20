<?xml version="1.0" encoding="UTF-8"?>
<ns2:response xmlns:ns2="http://www.i2b2.org/xsd/hive/msg/1.1/"
              xmlns:ns4="http://www.i2b2.org/xsd/cell/pm/1.1/"
              xmlns:ns3="http://www.i2b2.org/xsd/hive/msg/version/"
              xmlns:tns="http://ws.pm.i2b2.harvard.edu">
  <message_header>
    <i2b2_version_compatible>1.1</i2b2_version_compatible>
    <hl7_version_compatible>2.4</hl7_version_compatible>
    <sending_application>
      <application_name>PM Cell</application_name>
      <application_version>1.601</application_version>
    </sending_application>
    <sending_facility>
      <facility_name>i2b2 Hive</facility_name>
    </sending_facility>
    <receiving_application>
      <application_name>PM Cell</application_name>
      <application_version>1.5</application_version>
    </receiving_application>
    <receiving_facility>
      <facility_name>i2b2 Hive</facility_name>
    </receiving_facility>
    <datetime_of_message>2012-03-28T17:53:19.704Z</datetime_of_message>
    <message_control_id>
      <message_num>Pv4Jp6VAQ53ibEQ1Gznpv</message_num>
      <instance_num>1</instance_num>
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
  <response_header>
    <result_status>
      <status type="DONE">PM processing completed</status>
    </result_status>
  </response_header>
  <message_body>
    <ns4:configure>
      <environment>PRODUCTION</environment>
      <helpURL>http://www.i2b2.org</helpURL>
      <user>
        <full_name>i2b2 User</full_name>
        <user_name>${user?.userName}</user_name>
        <password token_ms_timeout="1800000" is_token="true">${q?.sessionId}</password>
        <domain>domain</domain>
        <is_admin></is_admin>
        <g:each in="${user.permissions}">
          <project id="${it?.study?.studyName}">
            <name>i2b2 Demo</name>
            <wiki>http://www.i2b2.org</wiki>
            <role>USER</role>
            <g:if test="${it?.allowPdo}">
              <role>DATA_AGG</role>
            </g:if>
            <role>DATA_OBFSC</role>
            <role>EDITOR</role>
          </project>
        </g:each>

      </user>
      <domain_name>HarvardDemo</domain_name>
      <domain_id>i2b2</domain_id>
      <active>true</active>
      <cell_datas>
        <cell_data id="CRC">
          <name>Data Repository</name>
          <url>${preference?.shrineCell}</url>
          <project_path>/</project_path>
          <method>REST</method>
          <can_override>true</can_override>
        </cell_data>
        <cell_data id="SHRINE">
          <name>Data Repository</name>
          <url>${preference?.shrineCell}</url>
          <project_path>/</project_path>
          <method>REST</method>
          <can_override>true</can_override>
        </cell_data>
        <cell_data id="ONT">
          <name>Ontology Cell</name>
          <url>${preference?.i2b2OntCell}</url>
          <project_path>/</project_path>
          <method>REST</method>
          <can_override>true</can_override>
        </cell_data>
      </cell_datas>
      <global_data/>
    </ns4:configure>
  </message_body>
</ns2:response>