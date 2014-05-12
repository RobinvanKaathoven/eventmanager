<%@ page import="net.luminis.university.eventmanager.Participant" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'participant.label', default: 'Participant')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-participant" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="list-participant" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <div>
        <g:select name="participantSelect" from="${participantInstanceList}" optionKey="id" />
    </div>

    <div>
        <select name="participantSelectAlt" id="participantSelectAlt">
            <g:each in="${participantInstanceList}" var="participantInstance">
                <option value="${participantInstance.id}">${participantInstance}</option>
            </g:each>
        </select>
    </div>
    
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="firstName"
                              title="${message(code: 'participant.firstName.label', default: 'First Name')}"/>

            <g:sortableColumn property="lastName"
                              title="${message(code: 'participant.lastName.label', default: 'Last Name')}"/>

        </tr>
        </thead>
        <tbody>
        <g:each in="${participantInstanceList}" status="i" var="participantInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show"
                            id="${participantInstance.id}">${fieldValue(bean: participantInstance, field: "firstName")}</g:link></td>

                <td>${fieldValue(bean: participantInstance, field: "lastName")}</td>

            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${participantInstanceTotal}"/>
    </div>
</div>
</body>
</html>
