package com.example.s7e3;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.*;


import com.example.s7e3.Funds;

@WebServlet(name = "createFund", value = "/create-fund")
public class CreateFund extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // get fund name from request
        String fund_name = request.getParameter("fund_name");

        // crate new fund
        Funds fund = new Funds(fund_name);

        // add fund to session
        HttpSession session = request.getSession(true);
        session.setAttribute(fund_name, fund);

        // update selected fund in session
        session.setAttribute("selected", fund_name);

        // create page
        PrintWriter out = response.getWriter();
        out.println("<HTML>\n" +
                "<HEAD><TITLE>Choose Action</TITLE></HEAD>\n" +
                "<BODY>\n" +
                "<h1>Fund Management</h1>" +
                "<h2>Fund \"" + fund.getName() + "\" created</h2>" +
                "<a href=\"form_create_fund.jsp\">1. Create a new equity fund</a><br/>\n" +
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