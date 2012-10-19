%{--<g:if test="${machine.endpointStatus== "Bad"}">--}%
%{--<div class="machine_status bad_machine">--}%

%{--</g:if>--}%
%{--<g:else>--}%
<div class="machine_status">
%{--</g:else>--}%
Machine: ${machine.realName}
<ul>
  <li>Address: ${machine.url}</li>
  <li>Status: ${machine.endpointStatus}</li>
  <li>Number of Patients: ${machine.latestStatus()?.numberOfPatients}</li>
  <li>Last Response Time(in millis): ${machine.latestStatus()?.responseTimeInMillis}</li>
</ul>
</div>