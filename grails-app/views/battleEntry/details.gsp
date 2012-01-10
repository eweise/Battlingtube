<%@ page import="java.text.DateFormat" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'battleDetails.css')}"/>
  

</head>
<body>
<div id="main-content">
  <div id="main-content_inner">
    <input type="hidden" name="battleEntryId" value="${battleEntry.id}"/>
    <h2>Video Details</h2>
    <div class="songBattle">

      <div id="song" class="picturedetails">
        <h3 class="title">${battleEntry.name}</h3>
        <ul class="details">
          <li><span>Battle: </span><g:link controller="battle" action="details" id="${battleEntry.battle.id}">${battleEntry.battle.name}</g:link></li>
          <li>
            <span>Submitted By: </span>${battleEntry.creator.username}
          </li>
          <li class="highestRound">
            <span>HighestRound: </span>${battleEntry.findHighestRound()?.roundIndex}
          </li>
          <g:if test="${!battleEntry.battle.notStarted()}">
            <li class="contestants">
              <span>Contestents in Round: </span>${battleEntry.findHighestRound()?.battleEntries.size()}
            </li>
            <li class="ranking">
              <span>Ranking: </span>${ranking?.score}
            </li>
          </g:if>
        </ul>
      </div>
      <g:youtubeLarge youtubeId="${battleEntry.youtubeId}"/>
    </div>
  </div>
  <p/><br/><br/><br/>
  <g:if test="${!battleEntry.battle.notStarted()}">
    <h3>Voting</h3>
    <table class="results">
      <tr>
        <th width="60px">DATE</th>
        <th width="60px">ROUND</th>
        <th width="30px">SCORE</th>
        <th>VOTER</th>
        <th>COMMENT</th>
      </tr>
      <g:each in="${battleEntry.votes}" var="vote">
        <tr>
          <td><g:dateFormat format="MM/dd/yy" value="${vote.createDate}"></g:dateFormat></td>
          <td>${vote.round.roundIndex + 1}</td>
          <td>${vote.score}</td>
          <td>${vote.voter.username}</td>
          <td>${vote.comment}</td>
        </tr>

      </g:each>

    </table>
  </g:if>
</div>
</body>
</html>
