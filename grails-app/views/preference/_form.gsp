<%@ page import="edu.chip.i2b2ssr.admin.data.Preference" %>



<div class="fieldcontain ${hasErrors(bean: preferenceInstance, field: 'preference', 'error')} ">
	<label for="preference">
		<g:message code="preference.preference.label" default="Preference" />
		
	</label>
	<g:select name="preference" from="${preferenceInstance.constraints.preference.inList}" value="${preferenceInstance?.preference}" valueMessagePrefix="preference.preference" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: preferenceInstance, field: 'value', 'error')} ">
	<label for="value">
		<g:message code="preference.value.label" default="Value" />
		
	</label>
	<g:textField name="value" value="${preferenceInstance?.value}"/>
</div>

