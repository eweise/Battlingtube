<div class="pager">

    <g:if test="${queryResult.currentPage > 1}">
        <g:link controller="${controller}" action="${action}"
                params="['page': queryResult.currentPage - 1]"><-- Previous</g:link>
        &nbsp;&nbsp;&nbsp;
    </g:if>
    [Page <span class="highlight">${queryResult.currentPage}</span>]
&nbsp;&nbsp;&nbsp;

<g:if test="${!queryResult.lastPage}">
    &nbsp;&nbsp;&nbsp;
    <g:link controller="${controller}" action="${action}"
            params="['page': queryResult.currentPage + 1]">Next --></g:link>
</g:if>
</div>