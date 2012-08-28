<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I2B2SSR Administration</title>
</head>

<body>
  <p>Listing of machines giving us issues</p>
  <g:each var="it" in="${machines}">
    <g:render template="machine_status" model="${[machine: it]}" />
  </g:each>

</body>
