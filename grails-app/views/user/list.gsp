<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I2B2SSR Administration</title>
</head>

<body>
<h1>User List</h1>

<div id="contentPane">
    <table id="userTable">
        <!-- Table header -->
        <thead>

        <th><g:link controller="user" action="create">
            <input type="button" value="Add New User" class="button"/>
        </g:link></th>
        <th>Username</th>
        <th>Real Name</th>
        <th>Home Sites</th>
        <th>Permissions</th>
        </thead>
        <!-- Table body -->
        <tbody>

        <g:each var="it" in="${users}" status="i">
            <tr id="user${it.id}" class="${(i % 2 == 0) ? 'even' : 'odd'}">
                <td><button class="deleteuser" data-username="${it.userName}" data-userid="${it.id}">Delete</button>
                </td>
                <td><g:link id="${it.id}" action="edit">${it.userName}</g:link><g:if test="${user?.isAdmin}">*</g:if></td>
                <td>${it.realName}</td>
                <td><ul>
                    <g:each var="study" in="${it.homesites}">
                        <li>${study.name}</li>
                    </g:each>

                </ul>
                </td>
                <td><ul>
                    <g:each var="permission" in="${it.permissions}">
                        <li><g:link controller="Permission" action="edit" params="[permissionId: permission.id]">${permission.study.studyName}</g:link>| <g:link controller="permission" action="delete" params="[permissionId: permission.id]">Delete </g:link></li>
                    </g:each>
                    <li><g:link controller="Permission" action="create" params="[userId: it.id]">Add New</g:link> </li>
                </ul></td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>

</body>
</html>