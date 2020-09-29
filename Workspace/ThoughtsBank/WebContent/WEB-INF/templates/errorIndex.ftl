<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thought Bank</title>
<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>

    <h4>Thought Bank Web Application</h4> 
    <div id = "journal">
        <div id = "login">
    <h2>Login</h2>
    <form action ="ThoughtBankServlet" method ="post">
        <input type="text" name="uname" size = "12" placeholder="Username"/> <br /><br/>
        <input type="text" name="password" size = "12" placeholder="Password"/> <br /><br/>
        <input type="submit" value="Login"  name="login" class="button"/>
        <p class = "error">Invalid username or password</p>
    </form>
        </div>
    </div>

</body>
</html>