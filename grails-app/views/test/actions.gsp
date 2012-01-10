<html>
<head>
  <title><g:title/></title>
  <meta name="layout" content="main"/>
</head>
<body>
<div id="main-content">

  <div id="main-content_inner">
    <h2>Test Actions</h2>
    <g:form action="advanceDays" controller="test">
      ${flash.message}
      <div id="input-form">

        <fieldset title="Change System Date">
          <ul>
            <li><label for="days">Enter Days to advance</label>
              <input tabindex="0" type="text" id="days" name="days" value="${days}"/>
          </ul>
        </fieldset>
        <div class="buttons">
          <span class="button"><input class="save" type="submit" value="Save"/></span>
        </div>
      </div>
      <p>
        <g:actionSubmit value="Check State" action="checkstate"/>
      </p>
    </g:form>
    <p>
      <g:form action="autoCreateBattles" controller="test">
        <g:radio name="autocreate" value="On" checked="${autocreate}"/>
        <g:radio name="autocreate" value="Off" />
          <g:actionSubmit value=" Auto Create Battle Jobs" action="autoCreateBattles"/>
      </g:form>
    </p>
  </div>
</div>
</body>
</html>