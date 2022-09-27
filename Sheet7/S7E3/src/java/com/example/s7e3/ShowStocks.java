package com.example.s7e3;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "showStocks", value = "/show-stocks")
public class ShowStocks extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // get selected fund name
        HttpSession session = request.getSession(true);
        String selcted_fund = (String)session.getAttribute("selected");

        Funds fund = (Funds)session.getAttribute(selcted_fund);
        ArrayList<Stocks> stocks = fund.getStocks();

        // create page
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n" +
                    "<HEAD><TITLE>Choose Action</TITLE></HEAD>\n" +
                    "<BODY>\n" +
                    "<h1>Fund Management</h1>" +
                    "<h2>Stocks of Fund \"" + fund.getName() + "\"</h2>");

        for(int i = 0; i < stocks.size(); i++) {
            out.println("Stock Name: " + stocks.get(i).getName() + "<br/>\n" +
                        "Dividend: " + stocks.get(i).getDividend() + "<br/>\n" +
                        "Quantity: " + stocks.get(i).getQuantity() + "<br/><br/>\n");
        }

        out.println("<a href=\"form_create_fund.jsp\">1. Create a new equity fund</a><br/>\n" +
                    "<a href=\"form_choose_fund.jsp\">2. Change the current equity fund</a><br/>\n" +
                    "<a href=\"form_search_stock.jsp\">3. Search for a stock with a particular name in the current equity fund</a><br/>\n" +
                    "<a href=\"form_add_stock.jsp\">4. Add a new stock to the current equity fund</a><br/>\n" +
                    "<a href=\"show-stocks\">5. Show all stock-objects of the current equity fund</a><br/>\n" +
                    "<a href=\"form_change_quantity.jsp\">6. Change the quantity of a stock of the current equity fund</a><br/>" +
                    "</BODY></HTML>");
    }

    public void destroy() {

    }

}
