<div id="userForm">
  <g:form action="save">
    <g:hiddenField name="id" value="${user?.id}"/>
    <table>
      <tr>
        <td><label for="userName">Username:</label></td>
        <td>
          <g:textField id="userName" name="userName" value="${user?.userName}"/>
        </td>
      </tr>
      <tr>
        <td>
          <label for="realName">Real name:</label>
        </td>
        <td>
          <g:textField id="realName" name="realName" value="${user?.realName}"/>
        </td>
      </tr>
      <tr>
        <td>
          <label for="institutionName">Institution:</label>
        </td>
        <td>
          <g:textField id="institutionName" name="institutionName" value="${user?.institutionName}"/>
        </td>
      </tr>
      <tr>
        <td>
          <label for="isAdmin">Administrator:</label>
        </td>
        <td>
          <g:checkBox name="isAdmin" value="${user?.isAdmin}"/>
        </td>
      </tr>
      <tr>
        <td>
          <label for="homesites">Home Sites:</label>
        </td>
        <td>
          <g:select name="homesites" id="homesites" from="${machines}" optionKey="id"
                    value="${user?.homesites*.id}"
                    optionValue="name" multiple="true"/>
        </td>
      </tr>
    </table>


    <g:submitButton name="save" value="save"/>
  </g:form>
</div>