<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"><head>
  <meta http-equiv="content-type" content="text/html; charset=ISO-8859-1">


<head>
  <g:javascript library="prototype"/>
  <g:javascript src="application.js"/>

  <g:render template="/shared/header"/></head>
<body>

<div id="wrapper">
  <g:render template="/shared/top"/>

  <div id="content">
    <g:render template="/shared/leftnav"/>
    <img src="${request.contextPath}/images/headerline.png" alt=""/>
    <g:layoutBody/>
    <g:render template="/shared/rightnav"/>
  </div><!-- close content -->

<g:render template="/shared/footer"/>

</div><!-- close wrapper -->

</body>
</html>