<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<#if needID == false>
 <form action ="MovieDatabaseServlet" method ="post">
        <input type="text" placeholder="Search for movie" name="movieSearch"/>
        <input type="submit" value="Search" name="movieSearchGo"/> 
    </form>
 </#if>
    
 
    <#if needID == true>
    enter the ID of the movie you want to review
    <form action ="MovieDatabaseServlet" method ="post">
        <input type="text" placeholder="movie ID" name="movieID"/>
        <input type="submit" value="Search" name="movieIDGo"/> 
    </form>
    
    <table>	
	 <tr><th>id</th><th>name</th><th>year</th></tr>
	<#list movies as movie>

	 <tr>
	 <td>${movie.id}</td>
	 <td>${movie.name}</td>
	 <td>${movie.year}</td>
	
	</tr>
	
	
	</#list>

</table>
    
    </#if>
</body>
</html>