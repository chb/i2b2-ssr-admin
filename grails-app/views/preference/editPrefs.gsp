<%@ page import="edu.chip.i2b2ssr.admin.data.Preference" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
<a href="#list-preference" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>


<div id="editPane">
    <g:form action="update">
        <table>
            <tr>
                <td>
                    <label for="shrineAddress">SHRINE Cell Address:</label>
                </td>
                <td>
                    <input size="70" type="text" id="shrineAddress" name="shrineCell"
                           value="${fieldValue(bean: preference, field: 'shrineCell')}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ontCell">Ontology Cell Address:</label>
                </td>
                <td>
                    <input size="70" type="text" id="ontCell" name="i2b2OntCell"
                           value="${fieldValue(bean: preference, field: 'i2b2OntCell')}"/>
                </td>
            </tr>
            <tr>
                  <td>
                      <label for="heartBeatStudy">SHRINE Heartbeat Study</label>
                  </td>
                  <td>
                      <g:select name="heartBeatStudy" from="${studies}"
                                value="${preference.heartBeatStudy?.id}"
                                noSelection="${['null':'Disable Heartbeat']}"
                                optionKey="id"
                                optionValue="studyName"
                                multiple="false"/>
                  </td>
              </tr>
        </table>

        <g:submitButton name="save" value="Save"/>
    </g:form>
</div>

</body>
</html>
