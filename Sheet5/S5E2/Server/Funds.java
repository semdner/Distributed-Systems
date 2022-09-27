package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Funds extends Remote {

    public Stocks searchStock(String name) throws RemoteException;

    public void addStock(String name, double dividend, int quantity) throws RemoteException;

    public ArrayList<Stocks> getStocks() throws RemoteException;

    public String getName() throws RemoteException;

}
