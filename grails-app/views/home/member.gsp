<html>
<head>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: '/custom-theme/jquery-ui-1.7.2.custom.css')}"/>
  <script type="text/javascript" src="${request.contextPath}/js/jquery-ui/jquery-1.3.2.min.js"></script>
  <script type="text/javascript" src="${request.contextPath}/js/jquery-ui/jquery-ui-1.7.2.custom.min.js"></script>
  <g:javascript library="jquery" />

  <style type="text/css">
  /*#editBattleContainer {*/
  /*visibility: hidden;*/
  /*}*/
  </style>

  <script type="text/javascript">

    $(document).ready(function() {
      $("#editBattleContainer").hide();

      $("#editBattleButton").click(function() {
        $("#editBattleContainer").toggle();
      });


    });
  </script>

</head>
<body>

<div id="main-content">
  <g:render template="/shared/messages"/>
  <h2 class="tableheader">Home</h2>

  <input id="editBattleButton" type="button" value="Click Here"> <div id="editBattleContainer">
  <g:render template="/battle/editBattleSnip"/>
</div>
  <ul id="recent-activity">


  </ul>
</ul>
</div>
</body>
</html>





