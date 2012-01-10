<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" %>
<html>
<head>
  <title><g:title/></title>
  <meta name="layout" content="main"/>
  <script type="text/javascript" src="${request.contextPath}/flash/audio-player.js"></script>
  <script type="text/javascript">
    AudioPlayer.setup("${request.contextPath}/flash/player.swf", {
      width: 140, autostart: "no"
    });
  </script>

</head>
<body>
<div id="main-content">
  <h2 class="tableheader">Top Videos</h2>
  <g:if test="${entries.size()>0}">
    <table class="dark">
      <tr>
        <th>VIDEO</th>
      </tr>
      <g:each var="entry" in="${entries}">
        <tr>
          <td><h3><g:link controller="battleEntry" action="details" id="${entry.id}">${entry.name}</g:link></h3>
          <td>
            <g:youtubeSmall youtubeId="${entry.youtubeId}"/>
          </td>
        </tr>
      </g:each>
    </table>
  </g:if>
  <g:else>
    <h3>No Battles Found</h3>
  </g:else>

</div>
</body></html>
