<%@ page import="grails.converters.JSON" %>

<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I2B2SSR Administration</title>
</head>

<body>
<legend>Status of entire network</legend>

<div class="contentPane">

    <table id="userTable" class="table table-striped">
        <!-- Table header -->
        <thead>
        <th>Name</th>
        <th>Status</th>
        <th>Response History</th>
        <th>Patient Count History</th>
        </thead>


        <!-- Table body -->
        <tbody>
        <g:each var="it" in="${machines}" status="i">
            <tr id="machine-${it.id}" class="${(i % 2 == 0) ? 'even' : 'odd'}">
                <td><g:link controller="machine" action="edit" id="${it.id}">${it.name}</g:link></td>
                <td>${it.endpointStatus}</td>
                <td class="graph" data-values="${it.statuses*.responseTimeInMillis as JSON}">
                    <div class="label"/>
                    <div class="chart"/>
                </td>
                <td class="graph" data-values="${it.statuses*.numberOfPatients as JSON}">
                    <div class="label"/>
                    <div class="chart"/>
                </td>
            </tr>
        </g:each>
        </tbody>

    </table>
    <g:paginate controller="status" action="index"
                total="${count}" max="10"/>

</div>
</body>
