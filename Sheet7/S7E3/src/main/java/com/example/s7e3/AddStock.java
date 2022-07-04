package com.example.s7e3;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "addStock", value = "/add-stock")
public class AddStock extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String stock_name = request.getParameter("stock_name");
        String dividend = request.getParameter("dividend");
        String quantity = request.getParameter("quantity");

        HttpSession session = request.getSession(true);
        String selected = (String)session.getAttribute("selected");
        Funds fund = (Funds)session.getAttribute(selected);

        fund.addStock(stock_name, Double.parseDouble(dividend), Integer.parseInt(quantity));

        PrintWriter out = response.getWriter();
        out.println("<HTML>\n" +
                "<HEAD><TITLE>Choose Action</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "<h1>Fund Management</h1>" +
                "<h2>Stock \"" + stock_name + "\" added</h2>" +
                "<a href=\"form_create_fund.jsp\">1. Create a new equity fund</a><br/>\n" +
                "<a href=\"form_choose_fund.jsp\">2. Change the current equity fund</a><br/>\n" +
                "<a href=\"form_search_stock.jsp\">3. Search for a stock with a particular name in the current equity fund</a><br/>\n" +
                "<a href=\"form_add_stock.jsp\">4. Add a new stock to the current equity fund</a><br/>\n" +
                "<a href=\"show-stocks\">5. Show all stock-objects of the current equity fund</a><br/>\n" +
                "<a href=\"form_change_quantity.jsp\">6. Change the quantity of a stock of the current equity fund</a><br/>"+
                "</BODY></HTML>");
    }

}
