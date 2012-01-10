<%@ page import="com.bt.domain.Battle" %>


<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <title>Battle List</title>
</head>
<body>
<div class="body">
  <h1>Battle List</h1>
  <g:if test="${flash.message}">
    <div class="message">${flash.message}</div>
  </g:if>
  <div class="list">
    <table>
      <thead>
      <tr>

        <g:sortableColumn property="category" title="Style of Music"/>

        <g:sortableColumn property="name" title="Title"/>

        <g:sortableColumn property="description" title="Description"/>

        <g:sortableColumn property="battle.battleEntries.size" title="Number of Players"/>

      </tr>
      </thead>
      <tbody>
      <g:each in="${battleList}" status="i" var="battle">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

          <td>${battle.category}</td>

          <td>${battle.name}</td>

          <td>${battle.description}</td>

          <td>${battle.battleEntries.size()}</td>

          <td><span class="button"><g:link action="create" controller="battleEntry" id="${battle.id}">Join</g:link></span></td>

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
