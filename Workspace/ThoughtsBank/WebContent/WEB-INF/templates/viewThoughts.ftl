<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thought Bank</title>
<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>

	<#include "header.ftl">

	<br/>
<div id = "wrapper">
<h4>number of thoughts ${count}</h4>
<table>	
	 <tr><th>Thought</th><th>Time</th></tr>
	<#list thoughts as thought>

	 <tr><td>
	${thought.thought}</td>
	<td>${thought.timestamp}</td>
	</tr>
	
	
	</#list>

</table>
<br/><br/>
<form action ="ThoughtBankServlet" method ="post">
	<input type="submit" name="clearthought" value = "Clear Thoughts" class = "button"/>
</form>
</div>
	
</body>
</html>	