<%@ page import="edu.chip.i2b2ssr.admin.data.User; edu.chip.i2b2ssr.admin.data.Study" %>
<div id="userForm">
    <g:form action="save">

            <g:hiddenField name="permissionId" value="${permission?.id}"/>


        <table>
            <tr>
                <td>
                    <label for="userId">Username:</label>
                </td>
                <td>
                    <g:if test="${user == null}">
                        <g:select size="5" name="userId"  from="${User.all}" optionKey="id"
                                  value="${user?.id}"
                                  optionValue="userName" multiple="false" disabled=""/>
                    </g:if>
                    <g:else>
                        <g:hiddenField name="userId" value="${user.id}"/>
                        ${user.realName}
                    </g:else>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="studyId">Study:</label>
                </td>
                <td>
                    <g:if test="${study == null}">
                        <g:select name="studyId" id="study" from="${availableStudies}" optionKey="id"
                                  value="${user?.permissions*.study*.id}"
                                  optionValue="studyName" multiple="false"/>
                    </g:if>
                    <g:else>
                        <g:hiddenField name="studyId" value="${study.id}"/>
                        ${study.studyName}
                    </g:else>
                </td>

            </tr>
            <tr>
                <td>
                    <label for="allowPdo">Allow PDO</label>
                </td>
                <td>
                    <g:checkBox name="allowPdo" value="${permission?.allowPdo}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="allowSiteIdentify">Allow Site Identify (Patients contain site origins)</label>
                </td>
                <td>
                    <g:checkBox name="allowSiteIdentify" value="${permission?.allowSiteIdentity}"/>
                </td>
            </tr>
        </table>

        <g:submitButton name="save" value="save"/>
    </g:form>
</div>