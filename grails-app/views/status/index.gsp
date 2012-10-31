<!doctype html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>I2B2SSR Administration</title>
</head>

<body>
<h2>Status of entire network</h2>

<div class="contentPane"">

  <table id="userTable">
    <!-- Table header -->
    <thead>
    <th>Real Name</th>
    <th>Address</th>
    <th>Status</th>
    <th>Last Number of Patients</th>
    <th>Last Response Time(in Millis)</th>
    </thead>
    <!-- Table body -->
    <tbody>
    <g:each var="it" in="${machines}" status="i">
      <tr id="machine" class="${(i % 2 == 0) ? 'even' : 'odd'}">
        <td>${it.realName}</td>
        <td>${it.url}</td>
        <td>${it.endpointStatus}</td>
        <td>${it.latestStatus()?.numberOfPatients}</td>
        <td>${it.latestStatus()?.responseTimeInMillis}</td>
      </tr>
    </g:each>
    </tbody>
  </table>
</div>
</body>
