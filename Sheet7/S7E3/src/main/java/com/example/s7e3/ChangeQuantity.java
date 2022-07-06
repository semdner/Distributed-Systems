package com.example.s7e3;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "changeQuantity", value = "/change-quantity")
public class ChangeQuantity extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // get selected fund
        HttpSession session = request.getSession(true);
        String search_stock = request.getParameter("stock_name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        String selected = (String)session.getAttribute("selected");
        Funds fund = (Funds)session.getAttribute(selected);

        Stocks stock = fund.searchStock(search_stock);
        stock.setQuantity(quantity);

        PrintWriter out = response.getWriter();

        try {
            out.println("<HTML>\n" +
                    "<HEAD><TITLE>Quantity Set</TITLE></HEAD>" +
                    "<BODY>\n" +
                    "<h1>Fund Management</h1>" +
                    "<h2>Quantity of Stock \"" + stock.getName() + "\" changed</h2>" +
                    "Stock Name: " + stock.getName() +
                    "<br> New Quantity: " + stock.getQuantity() + "<br/><br/>" +
                    "<a href=\"form_create_fund.jsp\">1. Create a new equity fund</a><br/>\n" +
                    "<a href=\"form_choose_fund.jsp\">2. Change the current equity fund</a><br/>\n" +
                    "<a href=\"form_search_stock.jsp\">3. Search for a stock with a particular name in the current equity fund</a><br/>\n" +
                    "<a href=\"form_add_stock.jsp\">4. Add a new stock to the current equity fund</a><br/>\n" +
                    "<a href=\"show-stocks\">5. Show all stock-objects of the current equity fund</a><br/>\n" +
                    "<a href=\"form_change_quantity.jsp\">6. Change the quantity of a stock of the current equity fund</a><br/>" +
                    "</BODY></HTML>");
        } catch(Exception e) {
            System.out.println(e);
            out.println("<HTML>\n" +
                    "<HEAD><TITLE>Quantity Set</TITLE></HEAD>" +
                    "<BODY>\n" +
                    "<h1>Fund Management</h1>" +
                    "<h2>Quantity of Stock \"" + stock.getName() + "\" couldn't be changed</h2>" +
                    "<a href=\"form_create_fund.jsp\">1. Create a new equity fund</a><br/>\n" +
                    "<a href=\"form_choose_fund.jsp\">2. Change the current equity fund</a><br/>\n" +
                    "<a href=\"form_search_stock.jsp\">3. Search for a stock with a particular name in the current equity fund</a><br/>\n" +
                    "<a href=\"form_add_stock.jsp\">4. Add a new stock to the current equity fund</a><br/>\n" +
                    "<a href=\"show-stocks\">5. Show all stock-objects of the current equity fund</a><br/>\n" +
                    "<a href=\"form_change_quantity.jsp\">6. Change the quantity of a stock of the current equity fund</a><br/>" +
                    "</BODY></HTML>");
        }
    }

}
