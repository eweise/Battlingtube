<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Show Round</title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}">Home</a></span>
    <span class="menuButton"><g:link class="list" action="list">Round List</g:link></span>
    <span class="menuButton"><g:link class="create" action="create">New Round</g:link></span>
</div>

<div class="body">
    <h1>Show Round</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
        <table>
            <tbody>

            <tr class="prop">
                <td valign="top" class="name">Id:</td>
                <td valign="top" class="value">${round.id}</td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">Battle:</td>
                <td valign="top" class="value"><g:link controller="battle" action="show"
                                                       id="${round?.battle?.id}">${round?.battle}</g:link></td>
            </tr>

            <tr class="prop">
                <td valign="top" class="name">Duration Days:</td>
                <td valign="top" class="value">${round.durationDays}</td>

            <tr class="prop">
                <td valign="top" class="name">Number Of Participants:</td>
                <td valign="top" class="value">${round.numberOfParticipants}</td>
            </tr>

            </tbody>
        </table>
    </div>

    <div class="buttons">
        <g:form controller="round">
            <input type="hidden" name="id" value="${round?.id}"/>
            <span class="button"><g:actionSubmit class="edit" value="Edit"/></span>
            <span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');"
                                                 value="Delete"/></span>
        </g:form>
    </div>
</div>
</body>
</html>
