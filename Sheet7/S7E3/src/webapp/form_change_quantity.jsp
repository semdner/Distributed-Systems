<%@ page import="com.example.s7e3.Funds" %><%--
  Created by IntelliJ IDEA.
  User: max
  Date: 04.07.22
  Time: 10:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String selected_fund = (String)session.getAttribute("selected");
    Funds fund = (Funds)session.getAttribute(selected_fund);
%>
<html>
<head>
    <title>Change Quantity Form</title>
</head>
<body>
<h1><%= "Search Stock for Fund: " + fund.getName() %></h1>
<form action="change-quantity">
    Stock Name: <input type="text" name="stock_name"><br/>
    New Quantity: <input type="text" name="quantity"><br/>
    <input type="submit" name="submit" value="submit">
</form>
</body>
</html>
