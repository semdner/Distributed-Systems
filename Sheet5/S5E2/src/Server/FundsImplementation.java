package Server;

import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class FundsImplementation extends UnicastRemoteObject implements Funds {
    private String name;
    ArrayList<StocksImplementation> stocks = new ArrayList<>();

    public FundsImplementation(String name) throws RemoteException {
        this.name = name;
    }

    public StocksImplementation searchStock(String name) throws RemoteException {
        for (int i = 0; i < stocks.size(); i++) {
            if(stocks.get(i).getName().equals(name)) {
                return stocks.get(i);
            }
        }
        return null;
    }

    public void addStock(String name, double dividend, int quantity) throws RemoteException {
        stocks.add(new StocksImplementation(name, dividend, quantity));
    }

    public ArrayList<StocksImplementation> getStocks() throws RemoteException {
        return stocks;
    }

    public String getName() throws RemoteException {
        return name;
    }

    public static void main(String[] args) throws RemoteException {
        try {
            FundsImplementation f1 = new FundsImplementation("Fund1");
            Naming.rebind ("Fund1", f1);
            FundsImplementation f2 = new FundsImplementation("Fund2");
            Naming.rebind ("Fund2", f2);
            StocksImplementation s1 = new StocksImplementation("Apple", 2.4, 3);
            Naming.rebind("Apple", s1);
            System.out.println("The server is up");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
