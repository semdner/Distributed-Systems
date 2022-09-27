package Receiver;

import org.w3c.dom.Text;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Receiver {

    public static void main(String[] args) throws Exception {
        Funds f1 = new Funds("Fund 1");
        f1.addStock("apple", 6.4, 10);
        f1.addStock("paypal", 3, 1);
        f1.addStock("tesla", 7, 100);

        Funds f2 = new Funds("Fund 2");
        f2.addStock("microsoft", 6.4, 1);
        f2.addStock("hp", 3, 100);
        f2.addStock("blackrock", 7, 1);

        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context context = new InitialContext(properties);

        TopicConnectionFactory connFactory =
                (TopicConnectionFactory)context.lookup("ConnectionFactory");

        TopicConnection conn = connFactory.createTopicConnection();
        conn.start();
        TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic qr = (Topic) context.lookup("dynamicTopics/topic1");
        Topic qs = (Topic) context.lookup("dynamicTopics/topic2");


        TopicSubscriber subscriber = session.createSubscriber(qr);
        TopicPublisher Publisher = session.createPublisher(qs);

        System.out.println("Server is up!");

        Scanner scanner = new Scanner(System.in);
        FundsMessageListener listener = new FundsMessageListener(Publisher, subscriber, session, f1, f2);
        subscriber.setMessageListener(listener);

        boolean terminate =  false;
        while (!terminate){
            subscriber.setMessageListener(listener);
            if(scanner.hasNext()){
                terminate = true;
            }
        }
        System.out.println("Server is terminated!");
        session.close();
        conn.close();
    }

}
