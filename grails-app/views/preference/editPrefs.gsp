<%@ page import="edu.chip.i2b2ssr.admin.data.Preference" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>

<div id="editPane">
    <g:form action="update">
        <fieldset>
            <legend>System Preferences</legend>

            <label class="control-label" for="shrineAddress">SHRINE Cell Address:</label>

            <input class="input-xxlarge" type="text" id="shrineAddress" name="shrineCell"
                   value="${fieldValue(bean: preference, field: 'shrineCell')}"/>


            <label class="control-label" for="ontCell">Ontology Cell Address:</label>

            <input class="input-xxlarge" type="text" id="ontCell" name="i2b2OntCell"
                   value="${fieldValue(bean: preference, field: 'i2b2OntCell')}"/>


            <div class="controls">
                <g:submitButton class="btn btn-primary" name="save" value="Save"/>
            </div>

        </fieldset>
    </g:form>
</div>

</body>
</html>
