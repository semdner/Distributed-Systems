package Sender;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class Sender {

    public static void main(String[] args) throws Exception {

        // Set middleware properties
        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context context = new InitialContext(properties);

        TopicConnectionFactory connFactory = (TopicConnectionFactory) context.lookup("ConnectionFactory");
        TopicConnection connection = connFactory.createTopicConnection();
        connection.start();
        TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = (Topic) context.lookup("dynamicTopics/topic1");

        TopicPublisher publisher = session.createPublisher(topic);

        // Create message
        TextMessage message = session.createTextMessage();

        message.setText("1|1|paypal,3.4,5");
        System.out.println("Sending Message: " + message.getText());

        // Send message
        publisher.publish(message);

        Topic topic2 = (Topic) context.lookup("dynamicTopics/topic2");

        // receive message
        TopicSubscriber subscriber = session.createSubscriber(topic2);
        connection.start();
        Message my_message = subscriber.receive();


        if(my_message instanceof TextMessage received) {
            System.out.println("Message Received: " + received.getText());
        }

        // Close session
        session.close();

        // Close connection
        connection.close();
    }

}
