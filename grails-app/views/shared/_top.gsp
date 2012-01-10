<div id="header">

    <div id="logo-tagline">

        <h1 id="logo"><a href="${request.contextPath}">Battling Tube</a></h1>

        <div id="search">
            <g:form controller="search" method="post">
                Search: <input type="text" id="text" name="text" value="${params['text'] == null ? '' : text}"/>
            </g:form>
        </div>
    </div>
</div>

