<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sweet Treats</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link href="https://fonts.googleapis.com/css?family=Ranga" rel="stylesheet">
<script type="text/javascript" src="js/index.js"></script>
<script src="https://use.fontawesome.com/d5984c18b9.js"></script>
<!--  <style>




#login {
    width: 300px;
    height: 300px;
    background-color: transparent;
    margin: auto;
    margin-top: 1em;
    padding-top: 10px;
    padding-bottom: 0px;
    position: relative;
}

#login2 {
    background-color: transparent;
    height: 100px;
    width: 100%;
    align-content: center;
    bottom: 10;
    padding-bottom: 0px;
}

input.text{
    height: 30px;
    width: 200px;
    text-align: center;
    margin-left: 50px;
    margin-top: 15px;
    border: 1px solid #222;
    background-color: #e6ccb3;
    border:2px solid #456879;
	border-radius:10px;
}
input.text:focus {
    height: 30px;
    width: 200px;
    text-align: center;
    margin-left: 50px;
    margin-top: 15px;
    border: 1px solid #222;
    background-color: #e6ccb3;
    border:2px solid gray;
	border-radius:10px;
}

.button {
	width: 200px;
   	margin-left: 50px;
    margin-top: 15px;
    border: 1px solid #222;
    background-color: #e6ccb3;
    border:2px solid #456879;
	border-radius:10px;
	transition: all 0.3s ease 0s;
	color: #757575;
}

.button:hover {	
  background: #456879;
  text-color: #000;
  color: white;
}

hr.style14 { 
  border: 0; 
  height: 1px; 
  background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);
  background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0); 
}

#error {
    text-align: center;
}
.header{
text-align: center;
font-family: 'Ranga', cursive;
font-size: 72px;
margin-bottom: 5px;
}

.subheader{
	text-align: center;
	font-family: 'Ranga', cursive;
	font-size: 32px;
	margin-bottom: 7px;
}
h2 {
	margin-top: 4px;
	text-align: center;
font-family: 'Ranga', cursive;
font-size: 50px;
margin-left: 25%;
margin-right: 25%;
}

body{
	background-color: #e6ccb3;
	background-image: url("https://d30y9cdsu7xlg0.cloudfront.net/png/190096-200.png");
}

.social-media-divider{
  border-color: #767676;
}

ul{
  display: inline;
  color: black;
  padding: 12px;
  margin-top: 5px;
  margin-bottom: 12px;
  padding-top: 5px;
}
.social-media-bar{
  text-align: center;
  display: inline;
}

.social-media{
  display: inline;
  padding: 5px;
  transition: all 0.3s ease 0s;
}
.social-icon{
  color: #767676;
  transition: all 0.3s ease 0s;
}
.social-icon:hover{
	color: #456879;
}
.copyright{
  text-align: left;
  display: inline;
  color: #767676;
}
.media-bar > li > a{
  &:hover, &:focus {
    color: black;
  }
}
.headerbackground{
	background-color: #e6ccb3;
	margin-left: 20%;
	margin-right: 20%;
	box-shadow: 0 1px 6px rgba(0,0,0,0.12), 0 1px 4px rgba(0,0,0,0.24);
}

#valid {
color: red;
}

</style>
-->
</head>
<body>
<br>
<div class="headerbackground">
<h1 class ="header"><strong>Welcome to Sweet Treats!</strong></h1>



   <div id ="login">
<form action ="LoginServlet" method ="post">
       <input type = "text" placeholder = "Username" name = "username" class="text"/>
   <br>
       <input type = "password" placeholder="Password" name = "password" class="text"/>
   <br>
   <#if !valid>
   <span id ="valid">Invalid username or password</span>
   </#if>
   <br>
  		<input type = "submit" value = "Login"  name ="login" class ="button" onClick = "verify()"/>
    <hr class="style14">
    <div id ="error"></div>
    <div id = "login2">
   		<input type = "submit" value = "Register" name = "register" class = "button"/>
    <br/>
		<input type = "submit" value = "Guest" name = "guestLogin" class = "button"/>
    </div>
    </form>
    </div>
</div>
    <br>
   <hr class="style14">
   <br>
	<ul>
    	<li class="copyright"><i class="fa fa-copyright"></i> Sweet Treats Inc.</li>
    	<li class="social-media-bar">
       	 <ul class="media-bar">
            <li class="social-media"><a href="http://facebook.com"> <i class="fa fa-facebook social-icon"></i></a></li>
            <li class="social-media"><a href="twitter.com"> <i class="fa fa-twitter social-icon"></i></a></li>
            <li class="social-media"><a href="googleplus.com"> <i class="fa fa-linkedin social-icon"></i></a></li>
            <li class="social-media"></li></li><a href="linkedin.com"> <i class="fa fa-google-plus social-icon"></i></a></li>
        </ul>
    </li>
</ul>
</body>
</html>