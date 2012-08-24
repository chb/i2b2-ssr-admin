<ns3:RoutingTableConfig xmlns:ns2="http://spin.org/xml/res/endpoint" xmlns:ns3="http://spin.org/xml/res">
    <g:each in="${studies}" var="study">
      <peerGroup>
        <groupName>${study.studyName}</groupName>
        <g:each in="${study.machines}" var="machine">
          <child>
            <endpointType>SOAP</endpointType>
            <address>${machine.url}</address>
          </child>
        </g:each>
      </peerGroup>
    </g:each>
</ns3:RoutingTableConfig>