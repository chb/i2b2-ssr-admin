<div id="userForm">
    <g:form action="save">
        <legend>Add a new SHRINE node to network</legend>
        <fieldset>
        <g:hiddenField name="id" value="${machine?.id}"/>

        <label for="name">Short Name(SHRINE Node Identifier):</label></td>

        <g:textField class="input-xlarge" id="name" name="name" value="${machine?.name}"/>

        <label for="realName">Long Name:</label>

        <g:textField class="input-xlarge" id="realName" name="realName" value="${machine?.realName}"/>


        <label for="url">SHRINE endpoint URL:</label>
        <g:textField class="input-xxlarge" id="url" name="url" value="${machine?.url}"/>



        <div class="controls">
            <g:submitButton class="btn btn-primary" name="save" value="save"/>
        </div>


        </fieldset>
    </g:form>
</div>