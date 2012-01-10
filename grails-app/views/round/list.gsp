<%@ page import="com.bt.domain.Round" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <title>Round List</title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}">Home</a></span>
    <span class="menuButton"><g:link class="create" action="create">New Round</g:link></span>
</div>

<div class="body">
    <h1>Round List</h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="Id"/>

                <th>Battle</th>

                <g:sortableColumn property="durationDays" title="Duration Days"/>

                <g:sortableColumn property="numberOfParticipants" title="Number Of Participants"/>

            </tr>
            </thead>
            <tbody>
            <g:each in="${roundList}" status="i" var="round">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show" id="${round.id}">${round.id}</g:link></td>

                    <td>${round.battle}</td>

                    <td>${round.durationDays}</td>

                    <td>${round.numberOfParticipants}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${Round.count()}"/>
    </div>
</div>
</body>
</html>
