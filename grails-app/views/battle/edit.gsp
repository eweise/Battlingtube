<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
</head>
<body>

<div id="main-content">
  <div id="main-content_inner">
    <h1>Create a Battle</h1>
    <g:if test="${flash.message}">
      <div class="message">${flash.message}</div>
    </g:if>
    <div class="dialog">
      <g:hasErrors bean="${battle}">
        <div class="errors">
          <g:renderErrors bean="${battle}" as="list"/>
        </div>
      </g:hasErrors>
      <g:form name="myform" action="save" method="post" enctype="multipart/form-data">
        <g:render template="battleFields" model="[battle:battle]"/>
        <g:savebutton formName="myform"/>
      </g:form>
    </div>
  </div>
</div>
</body>
</html>
