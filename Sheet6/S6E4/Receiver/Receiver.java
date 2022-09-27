package Receiver;

import org.w3c.dom.Text;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.Hashtable;

public class Receiver {

    public static void main(String[] args) throws Exception {
        // JMS properties
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context context = new InitialContext(properties);

        // search for queue
        TopicConnectionFactory connFactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");

        TopicConnection connection = connFactory.createTopicConnection();
        TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic t = (Topic) context.lookup("dynamicTopics/topic1");

        TopicSubscriber subscriber = session.createSubscriber(t);
        connection.start();

        // receive message
        Message message = subscriber.receive();

        // Create Funds
        Funds fund1 = new Funds("Fund1");
        fund1.addStock("apple", 6.7, 6);
        fund1.addStock("tesla", 3.2, 8);
        Funds fund2 = new Funds("Fund2");

        if(message instanceof TextMessage received) {
            Topic t2 = (Topic) context.lookup("dynamicTopics/topic2");

            TopicPublisher publisher = session.createPublisher(t2);

            System.out.println("Message Received: " + received.getText());

            // split message
            String[] received_str = received.getText().split("\\|");

            if(Integer.parseInt(received_str[0]) == 1) {

                switch (Integer.parseInt(received_str[1])) {
                    case 1 -> {
                        String[] stock = received_str[2].split(",");
                        fund1.addStock(stock[0], Double.parseDouble(stock[1]), Integer.parseInt(stock[2]));
                        TextMessage reply = session.createTextMessage();
                        reply.setText("Successfully added");
                        publisher.publish(reply);
                    }
                    case 2 -> {
                        ArrayList<String> names = fund1.getStocksNames();
                        TextMessage reply = session.createTextMessage();
                        StringBuilder reply_message = new StringBuilder();
                        for(int i = 0; i < names.size(); i++) {
                            if(i == names.size()-1) {
                                assert false;
                                reply_message.append(names.get(i));
                            } else {
                                assert false;
                                reply_message.append(names.get(i)).append("|");
                            }
                        }
                        assert false;
                        reply.setText(reply_message.toString());
                        publisher.publish(reply);
                    }
                    case 3 -> {
                        TextMessage reply = session.createTextMessage();
                        reply.setText(String.valueOf(fund1.searchStock(received_str[2]).getDividend()));
                        publisher.publish(reply);
                    }
                }


            } else if(Integer.parseInt(received_str[0]) == 2) {

                switch (Integer.parseInt(received_str[1])) {
                    case 1 -> {
                        String[] stock = received_str[2].split(",");
                        fund2.addStock(stock[0], Double.parseDouble(stock[1]), Integer.parseInt(stock[2]));
                        TextMessage reply = session.createTextMessage();
                        reply.setText("Successfully added");
                        publisher.publish(reply);
                    }
                    case 2 -> {
                        ArrayList<String> names = fund2.getStocksNames();
                        TextMessage reply = session.createTextMessage();
                        StringBuilder reply_message = new StringBuilder();
                        for(int i = 0; i < names.size(); i++) {
                            if(i == names.size()-1) {
                                assert false;
                                reply_message.append(names.get(i));
                            } else {
                                assert false;
                                reply_message.append(names.get(i)).append("|");
                            }
                        }
                        assert false;
                        reply.setText(reply_message.toString());
                        publisher.publish(reply);
                    }
                    case 3 -> {
                        TextMessage reply = session.createTextMessage();
                        reply.setText(String.valueOf(fund2.searchStock(received_str[2]).getDividend()));
                        publisher.publish(reply);
                    }
                }

            }

        }

        // Close session
        session.close();

        // Close connection
        connection.close();

    }

}
