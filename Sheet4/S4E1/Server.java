import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        Funds f1 = new Funds("f1");
        Funds f3 = new Funds("f2");
        Funds f2 = new Funds("f3");

        try {
            ServerSocket s_s = new ServerSocket(7899);

            while(true) {
                Socket c_s = s_s.accept();

                Connection c = new Connection(c_s, f1, f2, f3);
            }


        } catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }

}

class Connection extends Thread implements Serializable {

    Socket c_s;
    ObjectOutputStream object_os;
    ObjectInputStream object_is;
    Funds f1;
    Funds f2;
    Funds f3;

    public Connection(Socket c_s, Funds f1, Funds f2, Funds f3) throws IOException {
        this.c_s = c_s;
        this.object_os = new ObjectOutputStream(c_s.getOutputStream());
        this.object_is = new ObjectInputStream(c_s.getInputStream());
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.start();
    }

    public void run() {
        try {
            Message o = (Message) object_is.readObject();
            Message r = new Message();

            if(o.getFund() == 1) {
                switch(o.getMethod()) {
                    case 1:
                        f1.addStock(o.getName(), o.getDivident(), o.getQuantity());
                        r.setResponse("Successfully added");
                        object_os.writeObject(r);
                        break;
                    case 2:
                        for (Stocks s : f1.getStocks()) {
                            r.setResponse(s.getName());
                        };
                        object_os.writeObject(r);
                        break;
                    case 3:
                        r.setResponse(f1.getName());
                        object_os.writeObject(r);
                        break;
                    default:
                        r.setResponse("Error");
                        object_os.writeObject(r);
                        break;
                }
            } else if(o.getFund() == 2) {
                switch(o.getMethod()) {
                    case 1:
                        f2.addStock(o.getName(), o.getDivident(), o.getQuantity());
                        r.setResponse("Successfully added");
                        object_os.writeObject(r);

                    case 2:
                        for (Stocks s : f2.getStocks()) {
                            r.setResponse(s.getName());
                        };
                        object_os.writeObject(r);
                    case 3:
                        r.setResponse(f2.getName());
                        object_os.writeObject(r);
                        break;
                    default:
                        r.setResponse("Error");
                        object_os.writeObject(r);
                        break;
                }
            } else if(o.getFund() == 3) {
                switch(o.getMethod()) {
                    case 1:
                        f3.addStock(o.getName(), o.getDivident(), o.getQuantity());
                        r.setResponse("Successfully added");
                        object_os.writeObject(r);
                        break;
                    case 2:
                        for (Stocks s : f3.getStocks()) {
                            r.setResponse(s.getName());
                        };
                        object_os.writeObject(r);
                        break;
                    case 3:
                        r.setResponse(f3.getName());
                        object_os.writeObject(r);
                        break;
                    default:
                        r.setResponse("Error");
                        object_os.writeObject(r);
                        break;
                }
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

