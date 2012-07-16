<div id="userForm">
    <g:form action="save">
        <g:hiddenField name="id" value="${study?.id}"/>
        <table>
            <tr>
                <td>
                    <label for="studyName">Study Name:</label>
                </td>
                <td>
                    <g:textField id="studyName" name="studyName" value="${study?.studyName}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="studyDescription">StudyDescription:</label>
                </td>
                <td>
                    <g:textField id="studyDescription" name="studyDescription" value="${study?.studyDescription}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="machines">Machines in Study:</label>
                </td>
                <td>
                    <g:select name="machines" id="machines" from="${machines}" value="${study?.machines*.id}" optionKey="id" optionValue="name"
                              multiple="true"/>

                </td>
            </tr>
        </table>
        <g:submitButton name="save" value="save"/>
    </g:form>
</div>