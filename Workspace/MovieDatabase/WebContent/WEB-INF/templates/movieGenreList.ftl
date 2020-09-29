<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First Database</title>

</head>
<body>


Movies of ${genre} Genre
<table>	
	 <tr><th>id</th><th>name</th><th>year</th><th>rank</th></tr>
	<#list movieTitles as movie>

	 <tr>
	 <td>${movie.id}</td>
	 <td>${movie.name}</td>
	 <td>${movie.year}</td>
	 <td>${movie.rank}</td>
	
	</tr>
	
	
	</#list>

</table>

</div>
	
</body>
</html>	