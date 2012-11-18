<div id="userForm">
<legend>Add/Edit a User</legend>
<g:form action="save">
    <g:hiddenField name="id" value="${user?.id}"/>
    <label for="userName">Username:</label></td>
    <g:textField id="userName" name="userName" value="${user?.userName}"/>

    <label for="realName">Real name:</label>
    <g:textField id="realName" name="realName" value="${user?.realName}"/>

    <label for="institutionName">Institution:</label>
    <g:textField id="institutionName" name="institutionName" value="${user?.institutionName}"/>

    <label for="isAdmin">Administrator:</label>
    <g:checkBox name="isAdmin" value="${user?.isAdmin}"/>

    <label for="homesites">Home Sites:</label>
    <g:select name="homesites" id="homesites" from="${machines}" optionKey="id"
              value="${user?.homesites*.id}"
              optionValue="name" multiple="true"/>


    <div class="control-group">
    <g:submitButton class="btn btn-primary" name="save" value="save"/>
    </div>
</g:form>
</div>