<html>
<head>
  <title><g:title/></title>
  <meta name="layout" content="main"/>

</head>
<body>
<div id="main-content">
  <div id="main-content_inner">
    <g:renderErrors/>
    <g:form action="doLogin" method="post">
      <h2>Login</h2>
      <div class="dialog">

       <div class="errors"> ${flash.message} </div>
        <fieldset>
          <ul>
            <li>
              <label for="username">Username:</label>
              <input type="text" id="username" name="username" value="${user?.username}"/>
            </li>
            <li>
              <label for='password'>Password:</label>
              <input id="password" type="password" name="password" value="${user?.password}"/>
            </li>
            <li>
              <div class="buttons">
                <span class="formButton">
                  <input type="submit" value="Login"/>
                </span>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <span id="newuser">Are you a new user? <g:link controller="user" action="register">Register Here</g:link></span>
              </div>
            </li>
            <li>
            </li>
          </ul>
        </fieldset>
      </div>
    </g:form>
  </div>
</div>
</body></html>
