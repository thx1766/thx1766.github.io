<%@ page import="java.sql.*" %>
<%
	// load MySQL JDBC driver, connect to MySQL database, and create a statement object
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection cn = DriverManager.getConnection("jdbc:mysql://localhost/db336", "root", "");
	Statement st = cn.createStatement();
	ResultSet rs = null;
%>