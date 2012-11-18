<%@ page import="edu.chip.i2b2ssr.admin.data.User; edu.chip.i2b2ssr.admin.data.Study" %>
<div id="userForm">
    <g:form action="save">
        <legend>Add/Edit Permission</legend>
        <g:hiddenField name="permissionId" value="${permission?.id}"/>



        <label for="userId">Username:</label>
        <g:if test="${user == null}">
            <g:select size="5" name="userId" from="${User.all}" optionKey="id"
                      value="${user?.id}"
                      optionValue="userName" multiple="false" disabled=""/>
        </g:if>
        <g:else>
            <g:hiddenField name="userId" value="${user.id}"/>
            ${user.realName}
        </g:else>

        <label for="studyId">Study:</label>
        <g:if test="${study == null}">
            <g:select name="studyId" id="study" from="${availableStudies}" optionKey="id"
                      value="${user?.permissions*.study*.id}"
                      optionValue="studyName" multiple="false"/>
        </g:if>
        <g:else>
            <g:hiddenField name="studyId" value="${study.id}"/>
            ${study.studyName}
        </g:else>

        <label for="allowPdo">Allow PDO</label>
        <g:checkBox name="allowPdo" value="${permission?.allowPdo}"/>

        <label for="allowSiteIdentify">Allow Site Identify (Patients contain site origins)</label>
        <g:checkBox name="allowSiteIdentify" value="${permission?.allowSiteIdentify}"/>

        <div class="control-group">
            <g:submitButton class="btn btn-primary" name="save" value="save"/>
        </div>
    </g:form>
</div>