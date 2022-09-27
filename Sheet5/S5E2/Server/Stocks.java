package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Stocks extends Remote {

    public String getName() throws RemoteException;

    public double getDividend() throws RemoteException;

    public int getQuantity() throws RemoteException;

    public void setQuantity(int quantity) throws RemoteException;

}
