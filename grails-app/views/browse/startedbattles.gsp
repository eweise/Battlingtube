<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" %>
<html>
<head>
  <title><g:title/></title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="main-content">
  <h2 class="tableheader">Recently Started</h2>
  <table class="results">
    <tr>
      <th>TITLE</th>
      <th>&nbsp;</th>
      <th>LEADER</th>
    </tr>
    <g:each var="battle" in="${recentlyStarted}">
      <td class="border" colspan="3">&nbsp;</td>
      <tr>
        <td>${battle.name}
          <ol>
            <li>
              ${battle.battleEntries.size()} participants
            </li>
            <li>Ends 01/01/08</li>
          </ol>
        </td>
        <td>
          <div class="image-wrapper">
            <a href='${request.contextPath}/battle/details/${battle.id}'><img src='${battle.imageURL()}' alt=''/></a>
          </div>
        </td>
        <td>
          ${battle.findLeader()?.battleEntry?.name}
        </td>
      </tr>
    </g:each>
  </table>
</div>
</body></html>
