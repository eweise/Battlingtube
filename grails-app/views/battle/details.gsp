<html>
<head>
  <title><g:title/></title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="main-content">
  <div id="main-content_inner">
    <g:if test="${flash.message}">
      <div class="message"><h3 class="highlightLarge">${flash.message}</h3></div>
    </g:if>

    <h2>Battle Details</h2>
    <div class="battledetails">
      <h3 class="title">${battle.name}</h3>
      <ul class="details">

        <li>
          <span>Description: </span>${battle.description}
        </li>
        <li>
          <span>Created by: </span>${battle.creator.profile.username}
        </li>
        <li>
          <span>Number of Rounds: </span>${battle.rounds.size()}
        </li>
        <li>
          <span>Current Round: </span>${battle.currentRoundIndex()}
        </li>
        <li>
          <span>State: </span>${battle.state}
        </li>
        <g:if test="${battle.started() && battleEntry!=null}">
          <li style="margin-top:0px"><g:enterButton battleId="${battle.id}"/></li>
        </g:if>
        <g:if test="${battle.notStarted()}">
          <li style="margin-top:10px"><g:joinButton battleId="${battle.id}"/></li>
        </g:if>

      </ul>
    </div>
    <g:if test="${battle.notStarted()}">
      <p width="2em"/>
      <h3>Current Entries</h3>
      <table>
        <g:each var="battleEntry" in="${battle.battleEntries}">
          <tr>
            <td>
              <ul>
                <li>
                ${battleEntry.name}
                </li>
                <li>Entered By: ${battleEntry.creator.profile.username}</li>
              </ul>
            </td>
            <td>
              <g:youtubeSmall youtubeId="${battleEntry.youtubeId}"/>
            </td>
          </tr>
        </g:each>
      </table>
    </g:if>
    <g:set var="roundIndex" value="${battle.rounds.size()}"/>

    <g:if test="${roundIndex > 0}">
      <h3 >Rankings By Round</h3>
    </g:if>
    <g:while test="${roundIndex > 0}">
      <br/>
      <h4 style="float:left;"><span class="highlight">Round ${roundIndex}</span></h4>
      <table>
        <tr>
          <th width="200px"></th>
          <th></th>
        </tr>
        <g:set var="rank" value="1"/>
        <g:each var="ranking" in="${battle.rounds[roundIndex-1].findRankings()}">
          <tr <g:if test="${new Integer(rank) % 2 > 0}">class="odd"</g:if>>
            <td>
              <ul>
                <li>
                  <g:link controller="battleEntry" action="details" id="${ranking.battleEntry.id}">${ranking.battleEntry.name}</g:link> 
                </li>
                <li>
                  <span>Rank: </span>${rank}
                </li>
                <li><span>Score: ${ranking.score}</span></li>
              </ul>
            </td>
            <td><g:youtubeSmall youtubeId="${ranking.battleEntry.youtubeId}"/></td>
          </tr>
          <g:set var="rank" value="${new Integer(rank) + 1}"/>
        </g:each>
      </table>
      <g:set var="roundIndex" value="${new Integer(roundIndex)-1}"/>

    </g:while>
  <g:if test="${votingRecord.size()>0}">
    <div class="results">
      <h3>How You Voted</h3>
      <table class="results">
        <tr>
          <th width="60px">DATE</th>
          <th width="60px">VIDEO</th>
          <th width="30px">SCORE</th>
          <th>COMMENT</th>
        </tr>
        <g:each in="${votingRecord}" var="vote">
          <tr>
            <td><g:dateFormat format="MM/dd/yy" value="${vote.createDate}"></g:dateFormat></td>
            <td><g:link controller="battleEntry" action="details" id="${vote.battleEntry.id}">${vote.battleEntry.name}</g:link></td>
            <td>${vote.score}</td>
            <td>${vote.comment}</td>
          </tr>
        </g:each>

      </table>
    </div>
  </g:if>

  </div>
</div>
</body></html>
