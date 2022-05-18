package Client;

import Server.Funds;
import Server.FundsImplementation;
import Server.Stocks;

import java.rmi.Naming;
import java.util.Date;

public class Client {

    public static void main (String[] args) throws Exception {
        if (args.length != 1) {
            throw new IllegalArgumentException ("Syntax: DateClient <hostname>");
        }

        try {
            Funds rf1 = (Funds) Naming.lookup("rmi://" + args[0] + "/Fund1");
            System.out.println(rf1.getName());
            Funds rf2 = (Funds) Naming.lookup("rmi://" + args[0] + "/Fund2");
            System.out.println(rf2.getName());
            Stocks rs1 = (Stocks) Naming.lookup("rmi://" + args[0] + "/Apple");
            System.out.println(rs1.getName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
