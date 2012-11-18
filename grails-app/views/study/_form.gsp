<div id="userForm">
    <g:form action="save">
        <g:hiddenField name="id" value="${study?.id}"/>
         <legend>Add/Edit Study</legend>
        <label for="studyName">Study Name:</label>
        <g:textField id="studyName" name="studyName" value="${study?.studyName}"/>

        <label for="studyDescription">StudyDescription:</label>
        <g:textField class="input-xxlarge" id="studyDescription" name="studyDescription" value="${study?.studyDescription}"/>

        <label for="machines">Machines in Study:</label>
        <g:select name="machines" id="machines" from="${machines}" value="${study?.machines*.id}" optionKey="id"
                  optionValue="name"
                  multiple="true"/>


        <div class="control-group"></div>
        <g:submitButton class="btn btn-primary" name="save" value="save"/>
    </g:form>
</div>