<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bakery Title</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/register.css">
<link href="https://fonts.googleapis.com/css?family=Ranga" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
<style>
body {
	background-color: #e6ccb3;
	background-image: url("https://d30y9cdsu7xlg0.cloudfront.net/png/190096-200.png");
}

#header{
    width: 100%;
    height: 30px;
}

#wrapper {
  background-color: #FFFFFF;
  overflow: hidden;
  width: 100%;
  max-width: 800px;
  margin-right: auto;
  margin-left: auto;
  border:2px solid #456879;
  border-radius: 15px;
  height: 100%;
  align-content: center;
}



.form-style-7{
    max-width:400px;
    margin:50px auto;
    background:#fff;
    border-radius:2px;
    padding:20px;
    font-family: 'Open Sans', sans-serif;
}
.form-style-7 h1{
    display: block;
    text-align: center;
    padding: 0;
    margin: 0px 0px 20px 0px;
    color: #5C5C5C;
    font-size:x-large;
}
.form-style-7 ul{
    list-style:none;
    padding:0;
    margin:0;   
}
.form-style-7 li{
    display: block;
    padding: 9px;
    border:1px solid #DDDDDD;
    margin-bottom: 30px;
    border-radius: 3px;
}
.form-style-7 li:last-child{
    border:none;
    margin-bottom: 0px;
    text-align: center;
}
.form-style-7 li > label{
    display: block;
    float: left;
    margin-top: -19px;
    background: #FFFFFF;
    height: 14px;
    padding: 2px 5px 2px 5px;
    color: #B9B9B9;
    font-size: 14px;
    overflow: hidden;
    font-family: Arial, Helvetica, sans-serif;
}
.form-style-7 input[type="text"],
.form-style-7 input[type="date"],
.form-style-7 input[type="datetime"],
.form-style-7 input[type="email"],
.form-style-7 input[type="number"],
.form-style-7 input[type="search"],
.form-style-7 input[type="time"],
.form-style-7 input[type="url"],
.form-style-7 input[type="password"],
.form-style-7 textarea,
.form-style-7 select 
{
    box-sizing: border-box;
    -webkit-box-sizing: border-box;
    -moz-box-sizing: border-box;
    width: 100%;
    display: block;
    outline: none;
    border: none;
    height: 25px;
    line-height: 25px;
    font-size: 16px;
    padding: 0;
    font-family: 'Open Sans', sans-serif;
}
.form-style-7 input[type="text"]:focus,
.form-style-7 input[type="email"]:focus,
.form-style-7 input[type="password"]:focus,
.form-style-7 textarea:focus,
.form-style-7 select:focus 
{
}
.form-style-7 li > span{
    background: #F3F3F3;
    display: block;
    padding: 3px;
    margin: 0 -9px -9px -9px;
    text-align: center;
    color: #C0C0C0;
    font-family: 'Open Sans', sans-serif;
    font-size: 11px;
}
.form-style-7 textarea{
    resize:none;
}
.form-style-7 input[type="submit"],
.form-style-7 input[type="button"]{
    background: #2471FF;
    border: none;
    padding: 10px 20px 10px 20px;
    border-bottom: 3px solid #5994FF;
    border-radius: 3px;
    color: #D2E2FF;
    font-family: 'Open Sans', sans-serif;
}
.form-style-7 input[type="submit"]:hover,
.form-style-7 input[type="button"]:hover{
    background: #6B9FFF;
    color:#fff;
}

h1 {
font-family: 'Open Sans', sans-serif;
text-align: center;
}


</style>
</head>
<body>
<div id ="wrapper">
<!-- need javascript to make sure nothing is empty, would be cool to add a "verify password" field & check -->

<h1>Registration Form</h1>

<form class="form-style-7" action="LoginServlet" method="post">
<ul>
<li>
    <label for="name">First Name</label>
    <input type="text" name="f_name" maxlength="100">
    <span>Enter your first name here</span>
</li>
<li>
    <label for="l_name">Last Name</label>
    <input type="text" name="l_name" maxlength="100">
    <span>Enter your last name here</span>
</li>
<li>
    <label for="u_name">Username</label>
    <input type="text" name="u_name" maxlength="100">
    <span>Pick your desired username!</span>
</li>
<li>
    <label for="password">Password</label>
    <input type="password" name="password" maxlength="100">
    <span>Pick your desired password!</span>
</li>
<li>
    <label for="email">Email</label>
    <input type="text" name="email" maxlength="100">
    <span>Enter your email (johndoe@email.com)</span>
</li>
<li>
    <input type="submit" value="Register" name = "completeRegistration" />
</li>
</ul>
</form>

</body>
</html>