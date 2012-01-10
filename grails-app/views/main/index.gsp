<%@ page import="com.bt.domain.Battle; com.bt.domain.Battle" %>


<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Battle List</title>
</head>
<body>
<div class="nav">
  <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}">Home</a></span>
  <span class="menuButton"><g:link class="create" action="create">New Battle</g:link></span>
</div>
<div class="body">
  <h1>Battle List</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="list">
    <table>
      <thead>
      <tr>

        <g:sortableColumn property="id" title="Id"/>

        <g:sortableColumn property="currentStartTime" title="Current Start Time"/>

        <g:sortableColumn property="currentEndTime" title="Current End Time"/>

        <th>Battle Entries</th>

        <th>Creator</th>

      </tr>
      </thead>
      <tbody>
      <g:each in="${battleList}" status="i" var="battle">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td><g:link action="edit" id="${battle.id}">${battle.id}</g:link></td>

          <td>${battle.startTime}</td>

          <td>${battle.finishTime}</td>

          <td>${battle.battleEntries}</td>

          <td>${battle.creator}</td>

        </tr>
      </g:each>
      </tbody>
    </table>
  </div>
  <div class="paginateButtons">
    <g:paginate total="${Battle.count()}"/>
  </div>
</div>
</body>
</html>
