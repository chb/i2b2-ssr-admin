<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I2B2SSR Administration</title>
</head>

<body>
<h1>Study List</h1>

<div id="contentPane">
    <table id="studyTable">
        <!-- Table header -->

        <thead>
        <tr>
            <th>
                <g:link controller="study" action="create">
                    <input type="button" value="Add New Study" class="button"/>
                </g:link>
            </th>
            <th>Study Name</th>
            <th>Study Description</th>
            <th>Machines</th>
        </tr>
        </thead>
        <!-- Table body -->

        <tbody>

        <g:each var="it" in="${studies}" status="i">
            <tr id="user${it.id}" class="${(i % 2 == 0) ? 'even' : 'odd'}">
                <td><button class="deleteuser" data-studyname="${it.studyName}" data-studyid="${it.id}">Delete</button>
                </td>
                <td><g:link controller="study" action="edit" id="${it.id}">${it.studyName}</g:link></td>
                <td>${it.studyDescription}</td>
                <td><ul>
                    <g:each var="machine" in="${it.machines}">
                        <li>${machine.name}</li>
                    </g:each>

                </ul></td>
            </tr>
        </g:each>

        </tbody>
    </table>
</div>

</body>
</html>