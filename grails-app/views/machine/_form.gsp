<div id="userForm">
    <g:form action="save">
        <g:hiddenField name="id" value="${machine?.id}"/>
        <table>

            <tr>
                <td><label for="name">Short Name(SHRINE Node Identifier):</label></td>
                <td>
                    <g:textField id="name" name="name" value="${machine?.name}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="realName">Long Name:</label>
                </td>
                <td>
                    <g:textField id="realName" name="realName" value="${machine?.realName}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="url">SHRINE endpoint URL:</label>
                </td>
                <td>
                    <g:textField id="url" name="url" value="${machine?.url}" />
                </td>
            </tr>
            <tr>
                <td>
                    <label for="url">Certificate (Pase ASCII X.509):</label>
                </td>
                <td>
                    <g:textArea id="cert" rows="200" cols="300" name="cert" value="${machine?.certificate}" />
                </td>

            </tr>
        </table>

        <g:submitButton name="save" value="save"/>
    </g:form>
</div>