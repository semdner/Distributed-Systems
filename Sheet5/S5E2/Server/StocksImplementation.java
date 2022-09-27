package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StocksImplementation extends UnicastRemoteObject implements Stocks{

    private String name;
    private double dividend;
    private int quantity;

    public StocksImplementation(String name, double dividend, int quantity) throws RemoteException {
        this.name = name;
        this.dividend = dividend;
        this.quantity = quantity;
    }

    public String getName() throws RemoteException {
        return name;
    }

    public double getDividend() throws RemoteException {
        return dividend;
    }

    public int getQuantity() throws RemoteException {
        return quantity;
    }

    public void setQuantity(int quantity) throws RemoteException {
        this.quantity = quantity;
    }

}
