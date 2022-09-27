package Sender;

import javax.jms.*;
import javax.naming.*;
import java.util.Hashtable;

public class Sender {

    public static void main(String[] args) throws Exception {

        String message1 = "1|Fund 1|lg|5.5|20";
        String message2 = "2|Fund 1";
        String message3 = "3|Fund 1|apple";

        Hashtable<String, String> properties = new Hashtable<String, String>();
        properties.put(Context.INITIAL_CONTEXT_FACTORY,
                "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context context = new InitialContext(properties);

        TopicConnectionFactory connFactory =
                (TopicConnectionFactory)context.lookup("ConnectionFactory");

        TopicConnection conn = connFactory.createTopicConnection();
        TopicSession session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic ts = (Topic) context.lookup("dynamicTopics/topic1");

        TopicPublisher publisher = session.createPublisher(ts);
        TextMessage msg = session.createTextMessage();
        msg.setText(message2);
        System.out.println("Sending Message: "+ message2);
        publisher.send(msg);

        conn.start();
        Topic qr = (Topic) context.lookup("dynamicTopics/topic2");
        TopicSubscriber subscriber = session.createSubscriber(qr);
        Message returnValue = subscriber.receive();
        if(returnValue instanceof TextMessage){
            TextMessage txt = (TextMessage) returnValue;
            System.out.println("Return Value: " + txt.getText());
        }

        session.close();
        conn.close();
    }

}
