<html>
<head>
    <title><g:title/></title>
    <meta name="layout" content="main"/>

    <style type="text/css">
    .buttons {
        margin-top: 20px;
        margin-bottom: 20px;
    }

    #terms {
        margin-top: 10px;
        margin-left: -2px;
    }

    .formButton {
        margin-left: 20px;
    }

    </style>

</head>

<body>
<div id="main-content">
    <div id="main-content_inner">
        <div class="errors">
            <g:renderErrors/>
        </div>
        <g:form action="register" method="post">
            <h2>Register</h2>

            <div class="dialog">
                <fieldset>
                    <ul>
                        <li>
                            <label for="username">Username:</label>
                            <input tabindex="1" type="text" id="username" name="username" value="${profile?.username}"/>
                        </li>
                        <li>
                            <label for="email">Email Address:</label>
                            <input tabindex="2" type="text" id="email" name="email" value="${profile?.email}"/>
                        </li>
                        <li>

                            <label for='password'>Password:</label>
                            <input tabindex="3" id="password" type="password" name="password"
                                   value="${profile?.password}"/>
                        </li>
                        <li>

                            <label for='password2'>Confirm Password:</label>
                            <input tabindex="4" id="password2" type="password" name="password2"
                                   value="${profile?.password2}"/>
                        </li>
                        <li>
                            <input id="terms" type="checkbox" name="terms" value="true"/> <g:link controller="static"
                                                                                                  action="terms">I agree to the Terms of Service</g:link>
                        </li>
                    </ul>
                </fieldset>

                <div class="buttons">
                    <span class="formButton">
                        <input tabindex="5" type="submit" value="Register"/>
                    </span>
                </div>

                <div style="margin-top:20px">
                    Are you an existing user? <g:link controller="user" action="login">Login Here</g:link>
                </div>
            </div>
        </g:form>
    </div>
</div>
</body></html>
