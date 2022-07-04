<%@ page import="com.example.s7e3.Funds" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Funds fund1 = new Funds("fund1");
    fund1.addStock("apple", 3.4, 5);
    fund1.addStock("hp", 6.7, 8);
    fund1.addStock("microsoft", 9, 10);
    Funds fund2 = new Funds("fund2");
    fund2.addStock("paypal", 4.5, 6);
    fund2.addStock("tesla", 6.2, 7);

    session.setAttribute(fund1.getName(), fund1);
    session.setAttribute(fund2.getName(), fund2);
    session.setAttribute("selected", fund1.getName());

%>
<!DOCTYPE html>
<html>
<head>
    <title>Choose Action</title>
</head>
<body>
<h1><%= "Fund Management" %></h1>
<h2><%= "Choose Action" %></h2>
<a href="form_create_fund.jsp">1. Create a new equity fund</a><br/>
<a href="form_choose_fund.jsp">2. Change the current equity fund</a><br/>
<a href="form_search_stock.jsp">3. Search for a stock with a particular name in the current equity fund</a><br/>
<a href="form_add_stock.jsp">4. Add a new stock to the current equity fund</a><br/>
<a href="show-stocks">5. Show all stock-objects of the current equity fund</a><br/>
<a href="form_change_quantity.jsp">6. Change the quantity of a stock of the current equity fund</a><br/>
</body>
</html>