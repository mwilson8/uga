<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="style.css" />
<title>Thought Bank</title>


</head>
<body>

	<#include "header.ftl">
	<br/>
	<div id="wrapper">
	<form action ="ThoughtBankServlet" method ="post">
	<input type="text" name="thought" size = "80" placeholder="&quot;No matter what people tell you, words and ideas can change the world&quot;  --Robin Williams"/>
	<input type="submit" name="addthought" value = "Add Thought" class="button"/>
	<input type="submit" name="viewthought" value="View Thoughts" class="button"/>
</form>
    </div>
	
</body>
</html>	