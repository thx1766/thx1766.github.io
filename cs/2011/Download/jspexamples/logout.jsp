<html>
<head> <title> Logout </title> </head>
<body>
	
<%=session.getAttribute("current_user")%>, you have logged out. Bye!
<%
// invalidate the session
session.invalidate();
%>

<br>
<a href=logon.html> Login </a>
</body>
</html>