<!doctype html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>I2B2SSR Administration</title>
</head>

<body>
<h2>Status of entire network</h2>
<div class="status">

  <g:each var="it" in="${machines}">
    <g:render template="machine_status" model="${[machine: it]}"/>
  </g:each>
</div>

</body>
