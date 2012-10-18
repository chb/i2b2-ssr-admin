<g:if test="${machine.status== "Bad"}">
  <div class="machine_status bad_machine">

</g:if>
<g:else>
  <div class="machine_status"></g:else>
Machine: ${machine.realName}
<ul>
  <li>Address: ${machine.url}</li>
  <li>Status: ${machine.status}</li>
  <li>Number of Patients: ${machine.lastPatientCount}</li>
  <li>Last Response Time(in millis): ${machine.lastResponseTimeInMillis}</li>
</ul>
</div>