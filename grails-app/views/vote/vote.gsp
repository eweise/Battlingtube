<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
  <meta name="layout" content="main"/>
  <g:javascript library="prototype"/>
  <script type="text/javascript" src="${request.contextPath}/flash/audio-player.js"></script>
  <link rel="stylesheet" href="${createLinkTo(dir: 'css', file: 'vote.css')}"/>

</head>
<body>
<script type="text/javascript">
  var sMax;    // Isthe maximum number of stars
  var holder; // Is the holding pattern for clicked state

  // Rollover for image Stars //
  function rating(num) {
    sMax = 0;	// Isthe maximum number of stars
    for (n = 0; n < num.parentNode.childNodes.length; n++) {
      if (num.parentNode.childNodes[n].nodeName == "A") {
        sMax++;
      }
    }

    s = num.id.replace("_", ''); // Get the selected star
    a = 0;
    for (i = 1; i <= sMax; i++) {
      if (i <= s) {
        $("_" + i).className = "on";
        holder = a + 1;
        a++;
      } else {
        $("_" + i).className = "";
      }
    }
    $('vote.score').value = s
  }

  function validate() {
    var score = $('vote.score').value
    if (score < 1) {
      alert('Please enter a star rating')
    }
    else {
      document.voteForm.submit()
    }
  }


</script>
<div id="main-content">
  <div id="main-content_inner">
    <g:if test="${battleEntry == null}">
      ${flash.message}
    </g:if>
    <g:else>
      <g:form id="voteForm" name="voteForm" action="vote" method="post">
        <input type="hidden" name="battleEntryId" value="${battleEntry.id}"/>
        <h2>Battle Voting - <g:link controller="battle" action="details" id="${battleEntry.battle.id}"> ${battleEntry.battle.name}</g:link></h2>
        <div class="dialog vote">
          <h3>${battleEntry.name}</h3>
          <g:youtubeLarge youtubeId="${battleEntry.youtubeId}"/>
          <ul class="votebox">
            <!--<li class="title"><h3 class="message">Select a star rating and click the Vote button </h3></li>-->
            <li id="rating"><h3>Select a rating and click 'Vote'</h3>
              <div id="rateMe" title="Rate Me...">
                <a id="_1" title="ouch..." onmouseover="rating(this)"></a>
                <a id="_2" title="ehh..." onmouseover="rating(this)"></a>
                <a id="_3" title="not bad" onmouseover="rating(this)"></a>
                <a id="_4" title="like it" onmouseover="rating(this)"></a>
                <a id="_5" title="yeah!" onmouseover="rating(this)"></a>
              </div>
          <li>
            <input type="hidden" id="vote.score" name="vote.score" value="${vote.score}"/>
          </li>
          <li id="voteButton">
            <img  name="voteimage" src="${request.contextPath}/images/votebutton.png" alt="Vote"
                    onmousedown="document.voteimage.src = '${request.contextPath}/images/votebutton_on.png;'"
                    onmouseup="document.voteimage.src = '${request.contextPath}/images/votebutton.png;'" onclick="validate()"/>
          </li>
          <li id="comments">
            <label  >Comments</label><br/>
            <textarea name="vote.comment" rows="2" cols="62">${vote.comment}</textarea>
            </li>
          </ul>
        </div>
      </g:form>
      <br/>
      <div class="voteresults">
        <h3>How You Voted</h3>
        <table class="results">
          <tr>
            <th id="date">DATE</th>
            <th id="video">VIDEO</th>
            <th id="score">SCORE</th>
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
    </g:else>
  </div>
</div>
</body>
</html>
