package Client;

import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {

        try {
            Message m = new Message("Apple", 3.4, 1, 1, 1);
            // Message m = new Message("Tesla", 4.5, 2, 1, 1);
            // Message m = new Message("PayPal", 2.1, 3, 1, 1);
            // Message m = new Message(2, 1);

            int port = 7899;

            Socket s = new Socket(args[0], port);

            ObjectOutputStream object_ostream = new ObjectOutputStream(s.getOutputStream());

            ObjectInputStream object_istream = new ObjectInputStream(s.getInputStream());

            object_ostream.writeObject(m);

            Message rsp = (Message) object_istream.readObject();

            for (String st : rsp.getResponse()) {
                System.out.println(st);
            }

            object_ostream.close();
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }

    }
}

