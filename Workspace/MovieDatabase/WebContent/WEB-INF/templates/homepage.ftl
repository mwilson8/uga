<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action ="StudentDatabaseServlet" method ="post">
        <input type="submit" value="Log Out"  name="logOut" class="button"/>
</form>
 Welcome, ${user}
 
 <table>
 
 <tr><td>Session id</td><td>${sessionId}</td></tr>
 <tr><td>Creation  </td><td>${creationTime}</td></tr>
 <tr><td>Last Access</td><td>${lastAccess}</td></tr>
 <tr><td>Access Count</td><td>${accessCount}</td></tr>

 </table>
</body>
</html>


