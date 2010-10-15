<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>

        <!-- LOGIN PAGE -->
		<div id="loginBox" class="loginBox">
			<g:if test="${session?.user}">
				<div style="margin-top:20px">
					<div style="float-right;">
						<a href="#">Profile</a>
						<g:link controller="user" action="logout">Logout</g:link><br/>
					</div>
					Welcome back
					<span id="userFirstName">${session?.user?.firstName}!</span>
					<br/><br/>
					You have purchased (${session.user.purchasedSongs?.size() ?: 0}) songs.<br/>
				</div>
			</g:if>
			<g:else>
                <g:form
                    name="loginForm"
                    url="[controller:'user', action:'login']">
                     <div>Username:</div>
                     <g:textField name="login"></g:textField>
                     <div>Password:</div>
                     <g:passwordField name="password" />
                     <input type="submit" value="Login" />
                </g:form>
                <g:renderErrors bean="${loginCmd}"></g:renderErrors>
			</g:else>
		</div>

		<!-- USER REGISTRATION NAV LINK -->
        <div id="navPane">
            <g:if test="${session.user}">
                <ul>
                    <li><g:link contorller="user" action="music">My Music</g:link></li>
                    <li><g:link controller="store" action="shop">The Store</g:link></li>
                </ul>
            </g:if>
            <g:else>
                <div id="registerPane">
                    Need an account?
                    <g:link controller="user" action="register">Signup here</g:link>.
                </div>
            </g:else>
        </div>

    </body>
</html>

