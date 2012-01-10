<html>
<head>
  <meta name="layout" content="main"/>
</head>
<body>
<script type="text/javascript">

  function confirmCancel(name, id) {
    var answer = confirm('Are you sure you want to cancel battle ' + name);
    if (answer) {
      var form = document.getElementsByName('cancelform' + id);
      form[0].submit()
    }
  }

</script>
<div id="main-content">
  <h2 class="tableheader">Battles You Voted In</h2>
  <g:if test="${battleList.size()>0}">
    <table class="dark">
      <tr>
        <th>BATTLE</th>
        <th>LEADER</th>
        <th>DESCRIPTION</th>
      </tr>
      <g:each var="battle" in="${battleList}">
        <td class="border" colspan="4">&nbsp;</td>
        <tr>
          <td><g:battleLink battle="${battle}"/>
            <ul>
              <li>
                ${battle.battleEntries.size()} participants
              </li>
              <li>
                <g:printNumberOfRounds numberOfRounds="${battle.numberOfRounds}"/>
              </li>
              <li>
                ${battle.state}
              </li>
            </ul>
          </td>
          <td>
            <g:set var="leader" value="${battle.findLeader().battleEntry}"/>
            <g:youtubeSmall youtubeId="${leader?.youtubeId}"/>
            <br/>
            <g:link controller="battleEntry" action="details" id="${leader?.id}">${leader?.name}</g:link>
          </td>
          <td>
            ${battle.description}
          </td>
        </tr>
      </g:each>
    </table>
  </g:if>
  <g:else>
    <h3>No Battles Found.  <g:link controller="vote" action="list"><span class="highlight">Start Voting</span></g:link> </h3>
  </g:else>
</div>
</body>
</html>





