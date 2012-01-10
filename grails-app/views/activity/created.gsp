<html>
<head>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'created.css')}"/>

  <style type="text/css">

  li.entry {
    border: thin solid white;
    background: lightgray;
    color: black;
  }

  ul.stats {
    font-size: 80%;
  }

  th.created-name {
    width:50px;
  }
  th.created-stats {
    width:50px;
  }
  th.created-actions {
    width:50px;
  }

  </style>

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
  <g:render template="/shared/messages"/>

  <h2 class="tableheader">Battles You Created</h2>
  <g:if test="${battleList.size()>0}">
    <ul id="created">
    <g:each var="battle" in="${battleList}">

      <table class="entry">
      <tr>
      <th class="created-name">&nbsp;</th>
      <th class="created-stats">&nbsp;</th>
      <th class="created-actions">&nbsp;</th>

      </tr>
        <tr>
        <td>
          <h3><g:battleLink battle="${battle}"/></h3>
        </td>

        <td>
          <ul class="stats">
            <li>
              ${battle.battleEntries.size()} participants

            </li>
            <li>
              <g:printNumberOfRounds numberOfRounds="${battle.numberOfRounds}"/>
            </li>
            <li>
              ${battle.state}
            </li>
            <li>
              Category: ${battle.category}
            </li>
          </ul>
        </td>
        <td>
          <ul>
            <g:if test="${battle.notStarted()}">
              <g:form name="cancelform${battle.id}" controller="battle" action="cancel" method="post">
                <g:if test="${battle.battleEntries.size() >= 2}">
                  <li>
                    <g:link controller="battle" action="start" id="${battle.id}"><span class="actionCreate">Start</g:link></span>&nbsp;
                  </li>
                </g:if>
                <li>
                  <g:link controller="battle" action="edit" id="${battle.id}"><span class="actionEdit">Edit</span></g:link>
                </li>
                <input type="hidden" name="id" value="${battle.id}"/>
                <a onclick="confirmCancel('${battle.name}', '${battle.id}')" href="#" controller="battle" action="cancel" id="${battle.id}"><span class="actionDelete">Cancel</span></a>
              </g:form>
            </g:if>
          </ul>
        </td>
          </tr>
      </li>

      <g:if test="${battle.notStarted() && battle.battleEntries.size() < 2}">
        <li class="info" style="margin-top:2em">Two or more contestants needed to start battle</li>
      </g:if>
      </tr>
      </table>
      </li>
      </tr>
    </g:each>
    </ul>
  </g:if>
  <g:else>
    <h3>No Battles Found.  <g:link controller="battle" action="create"><span class="highlight">Create a Battle</span></g:link></h3>
  </g:else>
</div>
</body>
</html>





