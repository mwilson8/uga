<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bakery Title</title>
<link rel="stylesheet" type="text/css" href="home.css">
<link href="https://fonts.googleapis.com/css?family=Ranga" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
<style>

#header {
	background-color: black;
	color:white;
	}
	
  #intro {
    text-align: center;
    font-family: 'Ranga', cursive;
    font-size: 64px;
    color: black;
    margin-left: 1em;
    margin-right: 1em;
    margin-top: 50px;
    margin-left: 20%;
    margin-right: 20%;
}
body{
	background-color: #e6ccb3;
	background-image: url("https://d30y9cdsu7xlg0.cloudfront.net/png/190096-200.png");
}
.form-style-7 input[type="submit"]:hover,
.form-style-7 input[type="button"]:hover{
    background: #6B9FFF;
    color:#fff;
}
.heading{
    background-color: #e6ccb3;
    margin-left: 20%;
    margin-right: 20%;
    box-shadow: 0 1px 6px rgba(0,0,0,0.12), 0 1px 4px rgba(0,0,0,0.24);
}

input.button {
	font-size: 16px;
	font-family: 'Ranga', cursive;
	width: 200px;
   	
    border: 1px solid #222;
    background-color: #e6ccb3;
    border:2px solid #456879;
	border-radius:10px;
	transition: all 0.3s ease 0s;
	color: #757575;
    align-content: center;
}

input.button:hover {	
  background: #456879;
  color: white;
}

li {
    margin-bottom: 12px;
    list-style: none;
}
.item{
    font-family: 'Ranga', cursive;
    text-align: center;
}  
</style>
</head>
<body>
<#include "header.ftl">
<div id = "wrapper">
<div class="heading">    
    <h2 id="intro">Our Products</h2>

<#list products as product>
<form action ="MainServlet" method ="post" class="cart">
<ul>
    <li>
    
   <img src = "images/${product.SRC}" height = "200px" width="300px" text-align="center"/>
    <!--<img src = "https://www.getupandgobaked.com/wp-content/uploads/2015/03/smart-cookie-pic-copy.jpg" height = "200px" width="300px" text-align="center">-->
    <h2 class="item">${product.name}</h2>
    <h3 class="item">${product.description}</h3>
    <h3 class="item">$${product.price}</h3>
    <#if !user.guest>
<input type="submit" value="Add to cart" name = "addToCart" class="button"/>
<input type="hidden" name="id[]" value=$${product.ID} />
</#if>
</li>
</ul>    
</form>

</#list>
    
    </div>  
</div>

</div>

</body>
</html>