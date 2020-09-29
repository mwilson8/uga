<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First Database</title>

</head>
<body>


<#if type=="actors">

//generate table for actors

<#elseif type =="movies">

//generate table for movies

<#elseif type =="directors">

//generate table for directors

</#if>
<table>	
	 <tr><th>Thought</th><th>Time</th></tr>
	<#list thoughts as thought>

	 <tr><td>
	${thought.thought}</td>
	<td>${thought.timestamp}</td>
	</tr>
	
	
	</#list>

</table>

</div>
	
</body>
</html>	