<html>
<head>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="main-content">
  <h2 class="tableheader">Choose a Battle to Compete In</h2>
  <g:if test="${battles.size()>0}">
    <table class="dark">
      <tr>
        <th>CLICK</th>
        <th>BATTLE</th>
        <th>STYLE</th>
        <th>DESCRIPTION</th>
      </tr>
      <g:each var="battle" in="${battles}">
        <td class="border" colspan="4">&nbsp;</td>
        <tr>
          <td>
            <li style="margin-top:10px">
              <g:joinButton battleId="${battle.id}"/>
            </li>

          </td>
          <td><g:battleLink battle="${battle}"/>
            <ol>
              <li>
                ${battle.battleEntries.size()} participants
              </li>
              <li>
                <g:printNumberOfRounds numberOfRounds="${battle.numberOfRounds}"/>
              </li>
            </ol>
          </td>
          <td>
            ${battle.category}
          </td>
          <td>
            ${battle.description}
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
