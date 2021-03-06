<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>I2B2SSR Administration</title>
</head>

<body>
<legend>Participating SHRINE Nodes</legend>

<div id="contentPane">


    <table id="machineTable" class="table table-striped">
        <!-- Table header -->
        <thead>

        <th><g:link action="create">
            <input type="button" value="Add New Machine" class="btn btn-primary"/>
        </g:link></th>

        </th>
        <th>Machine Name</th>
        <th>Real Name</th>
        <th>SHRINE URL</th>
        <th>Participating in Studies:</th>
        </thead>
        <!-- Table body -->

        <tbody>

        <g:each var="it" in="${machines}" status="i">
            <tr id="machine${it.id}" class="${(i % 2 == 0) ? 'even' : 'odd'}">
                <td><button class="btn btn-small btn-danger" data-name="${it.name}"
                            data-machineid="${it.id}">Delete</button>
                </td>
                <td><g:link id="${it.id}" action="edit">${it.name}</g:link></td>
                <td>${it.realName}</td>
                <td>${it.url}</td>

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
  <g:paginate controller="machine" action="list"
              total="${count}" max="10"/>
</div>

</body>
</html>