<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I2B2SSR Administration</title>
</head>

<body>
<legend>Users</legend>

<div id="contentPane">
    <table id="userTable" class="table table-striped">
        <!-- Table header -->
        <thead>

        <th
        ><g:link controller="user" action="create">
            <input type="button" value="Add New User" class="btn btn-primary"/>
        </g:link>
        </th>
        <th>Username</th>
        <th>Real Name</th>
        <th>Home Sites</th>
        <th>Permissions</th>
        </thead>
        <!-- Table body -->
        <tbody>

        <g:each var="it" in="${users}" status="i">
            <tr id="user${it.id}">
                <td><button id="deleteuser" class="btn btn-danger" data-username="${it.userName}" data-userid="${it.id}">Delete</button>
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
                        <li><g:link  controller="Permission" action="edit" params="[permissionId: permission.id]">${permission.study.studyName}</g:link> <g:link  controller="permission" action="delete" params="[permissionId: permission.id]">x</g:link></li>
                    </g:each>
                    <li><g:link controller="Permission" action="create" params="[userId: it.id]">Add New</g:link> </li>
                </ul></td>
            </tr>
        </g:each>
        </tbody>
    </table>

  <g:paginate controller="user" action="list" total="${count}" max="10"/>
</div>

</body>
</html>