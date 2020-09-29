<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First Database</title>

</head>
<body>


Details for ${movieTitle} <br/> 
ID: ${movieID} <br/>

Reviews:
<table>	
	 <tr><th>id</th><th>name</th><th>year</th><th>rank</th></tr>
	<#list reviews as review>

	 <tr>
	 <td>${review.id}</td>
	 <td>${review.review}</td>
	
	</tr>
	
	
	</#list>

</table>
<br/>

<form action ="MovieDatabaseServlet" method ="post">
        
        <input type="text" name="movieReview" placeholder="Add a new review.." />
        <input type="submit" value="Submit Review"  name="movieReviewGo" class="button"/>
        
        <input type="text" name="movieReviewID" placeholder="Review ID" size ="8" />
        <input type="submit" value="Delete a Review"  name="movieReviewDelete" class="button"/>
        
        <input type="submit" value="Delete This Movie"  name="movieDelete" class="button"/>
</form>

</div>
	
</body>
</html>	