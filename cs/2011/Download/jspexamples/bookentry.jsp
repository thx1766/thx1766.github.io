<html>
<head> <title>Book entry</title> </head>
<body>
<%@ include file="secure" %>
<%@ include file="connect.jsp" %>
<%
	String bookname = request.getParameter("bookname");
	String author = request.getParameter("author");
	if(bookname != null && author != null) {
		st.executeUpdate("create table if not exists books(bookname varchar(50), author varchar(50))");
		st.executeUpdate("insert into books values('" + bookname + "','" + author + "')");
	}
%>

<script language="javascript">
	function validate(objForm){
		if(objForm.bookname.value.length==0) {
			alert("Please enter book name!");
			objForm.bookname.focus();
			return false;
		}
		if(objForm.author.value.length==0) {
			alert("Please enter author!");
			objForm.author.focus();
			return false;
		}
		return true;
	}
</script>
<center> <br>
<form action="bookentry.jsp" method="post" name="entry" onSubmit="return validate(this)">
<table frame="box" cellpadding="3">
	<tr> <td colspan="2" align="center"> <h2>Book entry</h2> </td> </tr>
	<tr> <td>Book name:</td> <td> <input name="bookname" type="text" size="50"> </td> </tr>
	<tr> <td>Author:</td> <td> <input name="author" type="text" size="50"> </td> </tr>
	<tr> <td colspan="2" align="center"> <input type="submit" value="Submit"> </td> </tr>
</table>
</form>

<h2>Book list</h2>
<table border="1" cellpadding="3">
<tr> <th>No</th> <th>Book name</th> <th>Author</th> </tr>
<%
	rs = st.executeQuery("select * from books");
	int no = 1;
	while(rs.next()) {
%>
<tr> <td> <%=no%> </td> <td> <%=rs.getString("bookname")%> </td> <td> <%=rs.getString("author")%> </td> </tr>
<%    
		no++;
	}
%>
<tr>
</table>
</center>

<a href=list.jsp> List of employees </a> <br>
<a href=bookentry.jsp> Input book details </a> <br>
<a href=search.jsp> Search for books </a> <br>
<a href=logout.jsp> Logout </a>

<%@ include file="close" %>
