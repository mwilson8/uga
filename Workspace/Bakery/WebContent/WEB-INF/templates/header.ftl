<div id = "header">

<h1>Sweet Treats</h1>
<#if user.guest> <h3>Welcome, Guest </h3> </#if>
<#if !user.guest> <h3>Welcome,  
${user.firstName} ${user.lastName} </h3>
 <form action ="MainServlet" method ="post">
<input type="submit" value="Logout" name = "logout" id="logout">
</form>
<#if !inCart>
<form action ="MainServlet" method ="post">
<input type="submit" value="View Cart" name = "viewCart" class="headerbtn"/>
</form>
</#if>
<#if inCart>
<form action ="MainServlet" method ="post">
<input type="submit" value="Back to store" name = "backToHome" class="headerbtn"/>
</form>
</#if>
</#if>
</div>