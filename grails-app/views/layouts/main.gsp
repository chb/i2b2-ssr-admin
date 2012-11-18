<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <r:require modules="bootstrap"/>
    <g:javascript library='jquery'/>
    <g:layoutHead/>
    <r:layoutResources/>
    <g:javascript src="jquery.flot.js"/>
</head>

<body>
<div  id="logo" role="banner"><img  src="${resource(dir: 'images', file: 'carranet.png')}" alt="carranet"/>

    <h3  style="margin-right: 5%;"class="pull-right">i2b2-ssr Control Panel</h3>
    <div class="welcomeBox">
        <g:if test="${session?.user}">
            Welcome, ${session.user.realName}:<g:link controller="user" action="logout">logout</g:link>
        </g:if>
    </div>

</div>

<div class="nav" role="navigation">
    <div class="navbar">
        <div class="navbar-inner">
            <ul class="nav">
                <li><a class="home" href="${createLink(uri: '/')}"><i class="icon-home"></i><g:message code="default.home.label"/></a></li>
                <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.name }}">
                    <g:if test="${c.hasProperty("menuName")}">
                        <li class="controller"><g:link
                                controller="${c.logicalPropertyName}">${c.getPropertyValue("menuName")}</g:link></li>
                    </g:if>
                </g:each>
            </ul>
        </div>
    </div>
</div>

<div class="container">
    <g:if test="${flash.message}">
        <div class="alert">${flash.message}</div>
    </g:if>
    <g:layoutBody/>
    <div class="footer" role="contentinfo"></div>

    <g:javascript library="application"/>
    <r:layoutResources/>
</div>
</body>
</html>