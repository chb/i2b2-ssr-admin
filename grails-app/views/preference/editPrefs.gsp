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
    <g:form action="updateAll">
        <table>
            <tr>
                <td>
                    <label for="ldapAddress">LDAP Server Address:</label>
                </td>
                <td>
                    <input type="text" id="ldapAddress" name="ldapAddre ss"
                           value="${fieldValue(bean: preference, field: 'ldapAddress')}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ldapDN">LDAP base Search DN:</label>
                </td>
                <td>
                    <input type="text" id="ldapDN" name="ldapBaseDN"
                           value="${fieldValue(bean: preference, field: 'ldapBaseDN')}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="shrineAddress">SHRINE Cell Address:</label>
                </td>
                <td>
                    <input type="text" id="shrineAddress" name="shrineCell"
                           value="${fieldValue(bean: preference, field: 'shrineCell')}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ontCell">Ontology Cell Address:</label>
                </td>
                <td>
                    <input type="text" id="ontCell" name="i2b2OntCell"
                           value="${fieldValue(bean: preference, field: 'i2b2OntCell')}"/>
                </td>
            </tr>
        </table>

        <g:submitButton name="save" value="Save"/>
    </g:form>
</div>

</body>
</html>
