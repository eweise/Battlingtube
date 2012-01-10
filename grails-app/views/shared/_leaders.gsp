<div class="leaders">
    <h3>Leaders</h3>
    <ul>
        <g:if test="${rankings.size >= 1}">

            <li>
                1st<br/>
                <ul class="battle">
                    <li>
                        <g:youtubeSmall youtubeId="${rankings[0].battleEntry.youtubeId}"/>
                    </li>
                    <li class="name">
                        ${rankings[0].battleEntry.name}
                    </li>
                </ul>
            </li>
        </g:if>
        <g:if test="${rankings.size >= 2}">
            <li>
                2nd<br/>
                <ul class="battle">
                    <li>
                        <g:youtubeSmall youtubeId="${rankings[1].battleEntry.youtubeId}"/>
                    </li>
                    <li class="name">
                        ${rankings[1].battleEntry.name}<br/>
                    </li>
                </ul>
            </li>
        </g:if>
        <g:if test="${rankings.size >= 3}">
            <li>
                3rd<br/>
                <ul class="battle">
                    <li>
                        <g:youtubeSmall youtubeId="${rankings[2].battleEntry.youtubeId}"/>
                    </li>
                    <li class="name">
                        ${rankings[2].battleEntry.name}<br/>
                    </li>
                </ul>
            </li>
        </g:if>
    </ul>
</div>
