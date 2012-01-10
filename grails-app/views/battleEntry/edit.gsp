<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>

   <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: '/custom-theme/jquery-ui-1.7.2.custom.css')}"/>
  <script type="text/javascript" src="${request.contextPath}/js/jquery-ui/jquery-1.3.2.min.js"></script>
  <script type="text/javascript" src="${request.contextPath}/js/jquery-ui/jquery-ui-1.7.2.custom.min.js"></script>

  <script type="text/javascript">
    $(document).ready(function() {

      $('#popup').dialog({height: 300, width:500, resizable:false, autoOpen:false, modal:true});

      $('#popupAnchor').bind("click", function(e) {
        $("#popup").dialog('open');
      });

    });
  </script>

</head>
<body>
<div id="main-content">
  <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
  <div id="main-content_inner">
  <div class="body">
    <h2>Join Battle - ${battle.name}</h2>

    <g:form name="myform" action="save" method="post">
      <g:hiddenField name="battleId" value="${fieldValue(bean:battle,field:'id')}"/>
      <div class="dialog" >
      <g:if test="${flash.message}">
        <div class="message"">${flash.message}</div>
      </g:if>
      <g:hasErrors bean="${battleEntry}">
        <div class="errors" style="position:relative;margin-top:20px;">
          <g:renderErrors bean="${battleEntry}" as="list"/>
        </div>
      </g:hasErrors>
      <g:render template="battleEntryFields" model="[battleEntry:battleEntry]"/>
      <div class="buttons">
        <g:savebutton formName="myform"/>
      </div>
      </div>
    </g:form>
  </div>
</div>
</div>
</body>
</html>
