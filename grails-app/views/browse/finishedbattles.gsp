<html>
<head>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="main-content">
  <h2 class="tableheader">Finished Battles</h2>
  <g:if test="${battles.size()>0}">
    <table>
      <tr>
        <th>BATTLE</th>
        <th>WINNER</th>
      </tr>
      <g:each var="battle" in="${battles}">
        <g:set var="leader" value="${battle.currentRound()?.findLeader()?.battleEntry}"/>
        <td class="border" colspan="4">&nbsp;</td>
        <tr>
          <td><h3><g:link controller="battle" action="details" id="${battle.id}">${battle.name}</g:link> </h3>
            <ul>
              <li>
                ${battle.battleEntries.size()} participants
              </li>
              <li>
                <g:printNumberOfRounds numberOfRounds="${battle.numberOfRounds}"/>
              </li>
              <li>
                Winner: <g:link controller="battleEntry" action="details" id="${leader?.id}">${leader?.name}</g:link>
              </li>
              <li>
                Entered by: ${leader?.creator?.username}
              </li>
            </ul>
          </td>
          <td>
            <g:youtubeSmall youtubeId="${leader?.youtubeId}"/>
          </td>
        </tr>
      </g:each>
    </table>
  </g:if>
  <g:else>
    <h3>No Battles Found</h3>
  </g:else>
</div>
</body>
</html>
