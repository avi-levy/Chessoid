<html>
<head>
<title>gTunes Store</title>
</head>
<body id="body">
    <h1>Registration</h1>
    <p>Complete the form below to create an account.</p>
    <g:hasErrors bean="${user}">
        <div class="errors">
            <g:renderErrors bean="${user}"></g:renderErrors>
            </div>
        </div>
    </g:hasErrors>

    <!-- REGISTRATION FORM -->
    <g:form action="register" name="registerForm">
        <div class="formField">
            <label for="login">Login:</label>
            <g:textField name="login"
                value="${user?.login}"/>
        </div>
        <div class="formField">
            <label for="login">Password:</label>
            <g:passwordField name="password"
                value="${user?.password}"/>
        </div>
        <div class="formField">
            <label for="passwordConfirm">Confirm Password:</label>
            <g:passwordField name="confirm"
                value="${user?.passwordConfirm}"/>
        </div>
        <div class="formField">
            <label for="firstName">First Name:</label>
            <g:textField name="firstName" value="${user?.firstName}"/>
        </div>
        <div class="formField">
            <label for="lastName">Last Name:</label>
            <g:textField name="lastName" value="${user?.lastName}"/>
        </div>
        <g:submitButton class="formButton" name="register" value="Register" />
    </g:form>

</body>
</html>

