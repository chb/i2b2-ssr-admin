<%@ page import="edu.chip.i2b2ssr.admin.data.Preference" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>

<div id="editPane">
    <g:form class="form-horizontal" action="update">
        <fieldset>
            <legend>System Preferences</legend>

            <div class="control-group">
                <label class="control-label" for="shrineAddress">SHRINE Cell Address:</label>

                <div class="controls">
                    <input size="70" type="text" id="shrineAddress" name="shrineCell"
                           value="${fieldValue(bean: preference, field: 'shrineCell')}"/>
                </div>
            </div>

            <div class="control-group">
                <label class="control-label" for="ontCell">Ontology Cell Address:</label>

                <div class="controls">
                    <input size="70" type="text" id="ontCell" name="i2b2OntCell"
                           value="${fieldValue(bean: preference, field: 'i2b2OntCell')}"/>
                </div>
            </div>


            <div class="control-group">
                <div class="controls">
                    <g:submitButton class="btn btn-primary" name="save" value="Save"/>
                </div>
            </div>
        </fieldset>
    </g:form>
</div>

</body>
</html>
