<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div id="main-content">
    <h2 class="tableheader">Battle Search</h2>
    <g:if test="${battles.size() > 0}">
        <table class="dark">
            <tr>
                <th>BATTLE</th>
                <th>LEADER</th>
            </tr>
            <g:each var="battle" in="${battles}">
                <tr>
                    <td><h3><g:link controller="battle" action="details" id="${battle.id}">${battle.name}</g:link></h3>
                        <ol>
                            <li>
                                ${battle.category}
                            </li>
                            <li>
                                ${battle.battleEntries.size()} participants
                            </li>
                            <g:if test="${battle.started()}">
                                <li>Round ${battle.currentRoundIndex()} of ${battle.numberOfRounds}</li>
                                <li>Round Ends <g:formatDate format="MM-dd-yy h:mm a"
                                                             date="${battle.currentRound().endDate}"/></li>
                                <li><g:enterButton battleId="${battle.id}"/></li>
                            </g:if>
                            <g:if test="${battle.notStarted()}">
                                <li><g:joinButton battleId="${battle.id}"/></li>
                            </g:if>
                        </ol>
                    </td>
                <td>

                    <g:if test="${battle.started() || battle.finished()}">
                        <g:set var="leader" value="${battle.findLeader()?.battleEntry}"/>
                        <g:youtubeSmall youtubeId="${leader.youtubeId}"/>
                        </td>
                    </g:if>
                    <g:else>
                        <li>
                            Battle is ${battle.state}
                        </li>
                    </g:else>
                </tr>
                <tr><td>&nbsp;</td></tr>
            </g:each>
        </table>
    </g:if>
    <g:else>
        <h3>No Battles Found</h3>
    </g:else>
</div>
</body>
</html>
