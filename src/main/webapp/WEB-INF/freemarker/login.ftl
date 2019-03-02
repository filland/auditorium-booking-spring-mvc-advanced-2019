<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login page</title>
    <link href="/resources/css/login.css" rel="stylesheet" type="text/css"/>

</head>
<body onload='document.loginForm.username.focus();'>
<div id="main">

    <div id="login-box">

        <h2>Login</h2>

        <#if error??>
            <div class="error">${error}</div>
        </#if>
        <#if message??>
            <div class="msg">${message}</div>
        </#if>

        <form name='loginForm' action="/login" method='POST'>

            <table>
                <tr>
                    <td>User:</td>
                    <td><input type='text' name='username' value=''></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='password'/></td>
                </tr>
                <tr>
                    <td colspan='2'><button name="submit" type="submit"
                                            value="submit">Login</button></td>
                </tr>
                <tr>
                    <td></td>
                    <td style="float: right"><a href="/registration">Sign up</a></td>
                </tr>
            </table>

        </form>
    </div>
</div>
</body>
</html>