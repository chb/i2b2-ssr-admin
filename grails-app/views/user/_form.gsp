<div id="userForm">
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:form action="update">
        <table>
            <tr>
                <td>
                    <label for="userName">Username:</label>
                </td>
                <td>
                    <input type="text" id="userName" name="ldapAddre ss"
                           value="${fieldValue(bean: user, field: 'userName')}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ldapDN">Real name:</label>
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
        </table>

        <g:submitButton name="save" value="Save"/>
    </g:form>
</div>