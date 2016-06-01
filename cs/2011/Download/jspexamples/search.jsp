<html>
<head> <title> Book search </title> </head>
<body>
<%@ include file="secure" %>
<%@ include file="connect.jsp" %>
<%
	String search = request.getParameter("search");
	String text = request.getParameter("text");
	if (search == null) {
		search = "bookname";
		text = "";
	}
%>
<center> <h2> Book search </h2> <br>
<form action="search.jsp" method="post">
<big>Search by </big>
<select name="search">
  <option value="bookname" <%= search.equals("bookname") ? "selected='selected'" : "" %>>Book name</option>
  <option value="author" <%= search.equals("author") ? "selected='selected'" : "" %>>Author</option>
</select>
<input type="text" name="text"> <input type="submit" value="Search">
</form>
<br>
<table border = "1" cellpadding="3">
<tr> <th>Book name</th> <th>Author</th> </tr>
<%
	rs = st.executeQuery("select * from books where " + search + " like '%" + text + "%'");
	while (rs.next())
		out.println("<tr> <td>" + rs.getString("bookname")+ "</td> <td>" + rs.getString("author") + "</td> </tr>");
%>
</table>
</center>
<a href=list.jsp> List of employees </a> <br>
<a href=bookentry.jsp> Input book details </a> <br>
<a href=search.jsp> Search for books </a> <br>
<a href=logout.jsp> Logout </a>

<%@ include file="close" %>