<html>
<head> <title> List of employees </title> </head>
<body>

<%@ include file="secure" %>
<%@ include file="connect.jsp" %>

<center>
<h2>List of employees</h2> <br> 

<%
	// execute a query, the result set is returned
	rs = st.executeQuery("select empid from emp");
	
	// iterate through all the records in the result set, and print out each record
	while (rs.next()) {
		out.println(rs.getString("empid") + "<br>");
	}
%>

<br>------------------<br>
</center>

<a href=list.jsp> List of employees </a> <br>
<a href=bookentry.jsp> Input book details </a> <br>
<a href=search.jsp> Search for books </a> <br>
<a href=logout.jsp> Logout </a>

<%@ include file="close" %>