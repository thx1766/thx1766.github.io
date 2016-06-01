<html>
<head> <title> Login </title> </head>
<body>
<%@ include file="connect.jsp" %>
<%
	String user, pass;
	// get parameters sent to this jsp page
	user = request.getParameter("username");
	pass = request.getParameter("password");

	// execute a query, the result set is returned
	rs = st.executeQuery("select count(*) as cnt from emp where empid = '" + user + "' and pwd = '" + pass + "'");

	// go to the first row in the result set
	rs.first();

	// get the value in the column "cnt" of the current row
	int c = rs.getInt("cnt");

	if (c == 1) {
		out.println("<b>Hello " + user + "!</b><br><br>");
		out.println("<a href=list.jsp> List of employees </a> <br>");
		out.println("<a href=bookentry.jsp> Input book details </a> <br>");
		out.println("<a href=search.jsp> Search for books </a> <br>");
		out.println("<a href=logout.jsp> Logout </a>");
		
		// set the attribute 'current_user' of the session to the user name
		session.setAttribute("current_user", user);
		// expire the current session after 300 seconds of inactivity
		session.setMaxInactiveInterval(300);
	} else {
		out.println("Wrong username and/or password! <br>");
		if (user!=null && pass!=null && user.length()>0)
			try {
				st.executeUpdate("insert into emp values ('" + user + "','" + pass + "')");
				out.println("Username was inserted successfully! <br>");
			} catch(Exception e) {
				out.println("Insertion failed: Username existed! <br>");
			}
		out.println("<a href=logon.html> Login </a>");
	}
%>
<%@ include file="close" %>