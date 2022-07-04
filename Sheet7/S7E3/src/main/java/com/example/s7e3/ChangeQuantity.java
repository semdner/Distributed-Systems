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
        String new_quantity = request.getParameter("quantity");

        PrintWriter out = response.getWriter();
        try {
            // get selected fund
            String selected_fund = (String)session.getAttribute("selected");
            Funds fund = (Funds)session.getAttribute(selected_fund);

            Stocks stock = fund.searchStock(search_stock);
            stock.setQuantity(Integer.parseInt(new_quantity));

            out.println("<HTML>\n" +
                    "<HEAD><TITLE>Stock Found</TITLE></HEAD>\n" +
                    "<BODY>\n" +
                    "<h1>Fund Management</h1>" +
                    "<h2>Quantity of Stock \"" + stock.getName() + "\" changed</h2>" +
                    "<a href=\"form_create_fund.jsp\">1. Create a new equity fund</a><br/>\n" +
                    "<a href=\"form_choose_fund.jsp\">2. Change the current equity fund</a><br/>\n" +
                    "<a href=\"form_search_stock.jsp\">3. Search for a stock with a particular name in the current equity fund</a><br/>\n" +
                    "<a href=\"form_add_stock.jsp\">4. Add a new stock to the current equity fund</a><br/>\n" +
                    "<a href=\"show-stocks\">5. Show all stock-objects of the current equity fund</a><br/>\n" +
                    "<a href=\"form_change_quantity.jsp\">6. Change the quantity of a stock of the current equity fund</a><br/>" +
                    "</BODY></HTML>");
        } catch(Exception e) {
            out.println("<HTML>\n" +
                    "<HEAD><TITLE>Stock Found</TITLE></HEAD>\n" +
                    "<BODY>\n" +
                    "<h1>Fund Management</h1>" +
                    "<h2>Stock \"" + search_stock + "\" not found</h2>" +
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
