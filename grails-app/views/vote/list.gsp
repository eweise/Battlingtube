<html>
<head>
  <meta name="layout" content="main"/>
  <style type="text/css">
    td#enter {
      width:40px;
    }
  </style>

</head>
<body>
<div id="main-content">
  <h2 class="tableheader">Choose a Battle to Vote In</h2>
  <g:if test="${queryResult.results.size()>0}">
    <table class="dark">
      <tr>
        <th>CLICK</th>
        <th>BATTLE</th>
        <th>LEADER</th>
      </tr>
      <g:each var="battle" in="${queryResult.results}">
        <tr>
          <g:set var="leader" value="${battle.findLeader()?.battleEntry}"/>
          <td id="enter">
            <g:enterButton battleId="${battle.id}"/>
          </td>
          <td><h3>
            <g:link controller="battle" action="details" id="${battle.id}">${battle.name}</g:link>
          </h3>

            <ol>
              <li>
                Category: ${battle.category}
              </li>
              <li>
                ${battle.battleEntries.size()} participants
              </li>
              <li>Round ${battle.currentRoundIndex()} of ${battle.numberOfRounds}</li>
              <li>Round Ends <g:formatDate format="MM-dd-yy h:mm a" date="${battle.currentRound().endDate}"/></li>
            </ol>
          </td>
          <td>
            <g:youtubeSmall youtubeId="${leader.youtubeId}"/>
          </td>
        </tr>
        <tr><td>&nbsp;</td></tr>
      </g:each>
      <tr>
        <td colspan=2><g:render template="/shared/pager" model="['controller':'vote','action':'list','queryResult':queryResult]"/></td>
      </tr>
    </table>
  </g:if>
  <g:else>
    <h3>No Battles Found</h3>
  </g:else>
</div>
</body>
</html>
