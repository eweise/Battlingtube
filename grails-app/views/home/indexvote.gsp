<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" %>
<html>
<head>
  <title><g:title/></title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="main-content">
  <h2 class="tableheader">Featured Battle</h2>

  <g:if test="${battle}">
    <table class="dark">
      <tr>
        <td width=100px;>
          <g:battleimage battleId="${battle.id}" action="next" controller="vote"/>
        </td>
        <td>${battle.name}
          <ol>
            <li>
              ${battle.battleEntries.size()} participants
            </li>
            <li>Round ${battle.currentRoundIndex()} of ${battle.numberOfRounds}</li>
            <li>Round Ends <g:formatDate format="MM-dd-yy h:mm a" date="${battle.currentRound().endDate}"/></li>
          </ol>
        </td>
      </tr>
    </table>
    <div style="margin-top:20px;">
      <h3>Leaders</h3>
      <ul class="leaders">
        <g:if test="${rankings.size >= 1}"><li>1st
          <g:songimage controller="battleEntry" action="details" battleEntryId="${rankings[0].battleEntry.id}"/>${rankings[0].battleEntry.name}<br/>
        </li></g:if>
        <g:if test="${rankings.size >= 2}"><li>2nd
          <g:songimage controller="battleEntry" action="details" battleEntryId="${rankings[1].battleEntry.id}"/>${rankings[1].battleEntry.name}<br/>
        </li></g:if>
        <g:if test="${rankings.size >= 3}"><li>3rd
          <g:songimage controller="battleEntry" action="details" battleEntryId="${rankings[2].battleEntry.id}"/>${rankings[2].battleEntry.name}<br/>
        </li></g:if>
        <g:if test="${rankings.size >= 4}"><li>4th
          <g:songimage controller="battleEntry" action="details" battleEntryId="${rankings[3].battleEntry.id}"/>${rankings[3].battleEntry.name}<br/>
        </li></g:if>
        <g:if test="${rankings.size >= 5}"><li>5th
          <g:songimage controller="battleEntry" action="details" battleEntryId="${rankings[4].battleEntry.id}"/>${rankings[4].battleEntry.name}<br/>
        </li></g:if>
      </ul>
    </div>
  </g:if>
</div>
</body></html>
