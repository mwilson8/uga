<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Info</title>
</head>
<body>

	<h1> Welcome ${user}! </h1>
	<#list 1..10 as i>
	<p> Our latest product is :
	${i}<a href = "${latestProduct.url}">${latestProduct.name}</a>! <br/>
	</p>
	</#list>
</body>
</html>	
