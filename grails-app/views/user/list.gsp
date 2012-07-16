<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I2B2SSR Administration</title>
</head>

<body>
<h1>User List</h1>

<div id="contentPane">

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <table id="userTable">
        <!-- Table header -->
        <thead>

        <th><g:link controller="user" action="create">
            <input type="button" value="Add New User" class="button"/>
        </g:link></th>
        <th>Username</th>
        <th>Real Name</th>
        <th>Projects</th>
        </thead>
        <!-- Table body -->

        <tbody>

        <g:each var="it" in="${users}" status="i">
            <tr id="user${it.id}" class="${(i % 2 == 0) ? 'even' : 'odd'}">
                <td><button class="deleteuser" data-username="${it.userName}" data-userid="${it.id}">Delete</button>
                </td>
                <td><g:link id="${it.id}" action="edit">${it.userName}</g:link></td>
                <td>${it.realName}</td>
                <td><ul>
                    <g:each var="study" in="${it.studies.sort()}">
                        <li>${study.studyName}</li>
                    </g:each>

                </ul>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>

</body>
</html>