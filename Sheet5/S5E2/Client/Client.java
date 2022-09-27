package Client;

import Server.Funds;
import Server.FundsImplementation;
import Server.Stocks;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Date;

public class Client {

    public static void main (String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException ("Syntax: Client <hostname>");
        }

        try {
            int tq = 0;
            Funds rf1 = (Funds) Naming.lookup("rmi://" + args[0] + "/Fund1");
            Funds rf2 = (Funds) Naming.lookup("rmi://" + args[0] + "/Fund2");

            rf1.addStock("HP", 5.5, 3);

            System.out.println("===Example-Output===");
            System.out.println("Fund name: " + rf1.getName());
            System.out.println("Stock name: " + rf1.getStocks().get(3).getName());
            System.out.println("Stock dividend: " + rf1.getStocks().get(3).getDividend());
            System.out.println("Stock quantity: " + rf1.getStocks().get(3).getQuantity());
            System.out .println("Stock reference: " + rf1.searchStock("HP"));
            System.out.println("Get name by reference: " + rf1.searchStock("HP").getName());
            rf1.searchStock("HP").setQuantity(12);
            System.out.println("Get quantity by reference: " + rf1.searchStock("HP").getQuantity() + "\n");
            System.out.println("====================\n");


            for(int i = 0; i < rf1.getStocks().size(); i++) {
                if(rf1.getStocks().get(i).getDividend() >= 5) {
                    tq += rf1.getStocks().get(i).getQuantity();
                }
            }

            for(int i = 0; i < rf2.getStocks().size(); i++) {
                if(rf2.getStocks().get(i).getDividend() >= 5) {
                    tq += rf2.getStocks().get(i).getQuantity();
                }
            }

            System.out.println("===Total-Quantity===");
            System.out.println("Total quantity: " + tq);
            System.out.println("====================");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
