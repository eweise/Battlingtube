<html>
<head>
  <meta name="layout" content="main"/>
</head>
<body>
<script type="text/javascript">

  function confirmDelete(name, id) {
    var answer = confirm('Are you sure you want to delete the entry ' + name);
    if (answer) {
      var form = document.getElementsByName('deleteform' + id);
      form[0].submit()
    }
  }

</script>

<div id="main-content">
  <g:render template="/shared/messages"/>
  <h2 class="tableheader">Battles You Joined</h2>
  <g:if test="${battleEntries.size()>0}">
      <table class="dark">                                                              
      <tr>
        <th>BATTLE ENTRY</th>
        <th></th>
        <th>BATTLE</th>
      </tr>
      <g:each var="entry" in="${battleEntries}">
        <tr>
          <td><h3><g:link controller="battleEntry" action="details" id="${entry.id}">${entry.name}</g:link></h3>
            <g:if test="${entry.battle.notStarted()}">
              <g:form name="deleteform${entry.id}" controller="battleEntry" action="delete" method="post">
              <li>
                <g:link controller="battleEntry" action="edit" id="${entry.id}"><span class="actionEdit">Edit</span></g:link>
              </li>
              <li>
                 <input type="hidden" name="id" value="${entry.id}"/>
                <g:link controller="battleEntry" action="deleteEntry" id="${entry.id}"><span class="actionDelete">Delete</span></g:link>
              </li>
              </g:form>
            </g:if>
          </ul>
          </td>
          <td>
            <g:youtubeSmall youtubeId="${entry.youtubeId}"/>
          </td>
          <td>
            <ul>
              <li><g:battleLink battle="${entry.battle}"/></li>
              <li>${entry.battle.state}</li>
              <li>
                <g:printNumberOfRounds numberOfRounds="${entry.battle.numberOfRounds}"/>
              </li>
            </ul>
          </td>
        </tr>
      </g:each>
    </table>
  </g:if>
  <g:else>
    <h3>No Battles Found.  <g:link controller="browse" action="newbattles"><span class="highlight">Join a Battle</span></g:link></h3>
  </g:else>
</div>
</body>
</html>





