<%@ page import="com.bt.domain.Battle; com.bt.domain.Battle" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Create Round</title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list">Round List</g:link></span>
</div>

<div class="body">
    <h1>Create Round</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${round}">
        <div class="errors">
            <g:renderErrors bean="${round}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save" method="post">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="battle">Battle:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: round, field: 'battle', 'errors')}">
                        <g:select optionKey="id" from="${Battle.list()}" name="battle.id"
                                  value="${round?.battle?.id}"></g:select>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="durationDays">Duration Days:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: round, field: 'durationDays', 'errors')}">
                        <input type="text" id="durationDays" name="durationDays"
                               value="${fieldValue(bean: round, field: 'durationDays')}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="numberOfParticipants">Number Of Participants:</label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: round, field: 'numberOfParticipants', 'errors')}">
                        <input type="text" id="numberOfParticipants" name="numberOfParticipants"
                               value="${fieldValue(bean: round, field: 'numberOfParticipants')}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="Create"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
