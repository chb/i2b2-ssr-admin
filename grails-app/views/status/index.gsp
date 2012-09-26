<!doctype html>
<html>
<head>
  <meta name="layout" content="main"/>
  <title>I2B2SSR Administration</title>
  <style type="text/css">
  div.ex {
    width: 97%;
    padding: 10px;
    margin: 0px;
  }
  </style>
</head>

<body>
<div class="ex">
  <p>Listing of machines giving us issues</p>
  <g:each var="it" in="${machines}">
    <g:render template="machine_status" model="${[machine: it]}"/>
  </g:each>
</div>

</body>
