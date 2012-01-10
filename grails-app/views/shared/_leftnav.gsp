<%@ page import="com.bt.domain.BattleEntry; com.bt.domain.Battle" %>
<div id="col-left">
    <img src="${request.contextPath}/images/leftnavline.png" alt=""/>

    <div id="menu">
        <br/>
        <ul>
            <g:loggedIn>
                <li class="menu-item-startsub"><g:link controller="home" action="index">HOME</g:link></li>
            </g:loggedIn>
            <li class="menu-item-startsub"><g:link controller="vote" action="list">PLAY</g:link></li>
            <ul class="sub-menu">
                <li><g:link controller="vote" action="list">Vote in Battles</g:link></li>
                <li><g:link controller="battle" action="create">Create a Battle</g:link></li>
                <li><g:link controller="browse" action="newbattles">Compete in Battles</g:link></li>
            </ul>
            <li class=" menu-item-endsub menu-item-startsub"><g:link controller="browse"
                                                                     action="topvideos">FIND</g:link></li></li>
            <ul class="sub-menu">
                <li><g:link controller="browse" action="topvideos">Top Videos</g:link></li>
                <li><g:link controller="browse" action="finishedbattles">Finished Battles</g:link></li>
            </ul>
            <g:loggedIn>
                <li class="menu-item-startsub menu-item-endsub"><g:link controller="activity"
                                                                        action="voted">BATTLE ACTIVITY</g:link></li>
                <ul class="sub-menu">
                    <li><g:link controller="activity" action="voted">Voted (<span
                            class="highlight">${Battle.countVoted(session.user)}</span>)</g:link></li>
                    <li><g:link controller="activity" action="joined">Joined (<span
                            class="highlight">${BattleEntry.countJoined(session.user)})</span></g:link></li>
                    <li><g:link controller="activity" action="created">Created (<span
                            class="highlight">${Battle.countCreated(session.user)})</span></g:link></li>
                </ul>
            </g:loggedIn>
            <g:if test="${session.username}">
                <li class="menu-item-endsub"><g:link controller="user"
                                                     action="logout">Logout ${session.username}</g:link></li>
            </g:if>
            <g:else>
                <li class="menu-item-startsub"><g:link controller="user" action="login">MEMBER</g:link></li>
                <ul class="sub-menu">
                    <li><g:link controller="user" action="login">Login</g:link></li>
                    <li><g:link controller="user" action="register">Register</g:link></li>
                </ul>
                <li class="menu-item-endsub">&nbsp;</li>
            </g:else>
        </ul>
    </div>

</div><!-- close side nav -->
