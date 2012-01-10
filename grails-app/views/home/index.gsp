<html>
<head>
  <title><g:title/></title>
  <meta name="layout" content="main"/>

  <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'home.css')}"/>

</head>
<body>
<div id="main-content">
  <div id="main-content_inner">
    <h2>Welcome</h2>
    <div class="intro">
      <p>
      <h3 class="message">Compete against other players for the best <a href="http://www.youtube.com">YouTube</a> videos and vote for your favorites.</h3>
    </p>
    </div>
  <div id="featuredbattle" >
  <div class="picturedetails" >
    <g:if test="${battle}">
      <h3 class="title">FEATURED BATTLE: <g:battleLink battle="${battle}"/></h3>
      <ul class="details" >

        <li>
          Description: ${battle.description}
        </li>
        <li>
          Round: ${battle.currentRoundIndex()} of ${battle.rounds.size()}
        </li>
        <li>
          State: ${battle.state}
        </li>
        <g:if test="${battle.started()}">
          <li style="margin-top:0px"><g:enterButton battleId="${battle.id}"/></li>
        </g:if>
        <g:if test="${battle.notStarted()}">
          <li style="margin-top:10px"><g:joinButton battleId="${battle.id}"/></li>
        </g:if>

      </ul>
      </div>
      <g:if test="${!battle.notStarted()}">
        <g:render template="/shared/leaders"/>
      </g:if>
    </g:if>
  </div>
    </div>
  <br/><br/>
</div>
</body></html>
