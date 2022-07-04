package Receiver;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.ArrayList;
import java.util.Hashtable;

public class FundsMessageListener implements MessageListener {

    private TopicPublisher publisher;
    private TopicSubscriber subscriber;
    private TopicSession session;
    private Funds f1;
    private Funds f2;

    public FundsMessageListener(TopicPublisher publisher, TopicSubscriber subscriber,  TopicSession session, Funds f1, Funds f2) throws Exception{
        this.publisher = publisher;
        this.subscriber = subscriber;
        this.session = session;
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage) {
            TextMessage txt = (TextMessage) message;
            try {
                System.out.println("Message Received: "+txt.getText());


                String massage = txt.getText();
                String [] content = massage.split("\\|", -1);
                String method = content[0];


                switch (Integer.parseInt(method)){
                    case 1 :
                        if (content[1].equals("Fund 1")){
                            f1.addStock(content[2], Double.parseDouble(content[3]),
                                    Integer.parseInt(content[4]));

                            TextMessage returnMsgF1_1 = session.createTextMessage();
                            returnMsgF1_1.setText("Stock " + content[2] + " has been added to Fund 1.");
                            publisher.send(returnMsgF1_1);
                        }
                        else {
                            f2.addStock(content[2], Double.parseDouble(content[3]),
                                    Integer.parseInt(content[4]));

                            TextMessage returnMsgF2_1 = session.createTextMessage();
                            returnMsgF2_1.setText("Stock " + content[2] + " has been added to Fund 2.");
                            publisher.send(returnMsgF2_1);
                        }
                        break;

                    case 2 :
                        if (content[1].equals("Fund 1")){
                            TextMessage message1 = session.createTextMessage();
                            ArrayList<String> names = f1.getStocksNames();
                            String text = "";
                            for(int i = 0; i<names.size(); i++) {
                                text += names.get(i) + ", ";
                            }
                            message1.setText(text);
                            publisher.send(message1);
                        }else {
                            TextMessage message2 = session.createTextMessage();
                            ArrayList<String> names = f2.getStocksNames();
                            String text = "";
                            for(int i = 0; i<names.size(); i++) {
                                text += names.get(i) + ", ";
                            }
                            message2.setText(text);
                            publisher.send(message2);
                        }
                        break;

                    case 3 :
                        if (content[1].equals("Fund 1")){
                            Stocks stock = f1.searchStock(content[2]);
                            TextMessage returnMsgF1_3 = session.createTextMessage();
                            returnMsgF1_3.setText(Double.toString(stock.getQuantity()));
                            publisher.send(returnMsgF1_3);
                        }else {
                            Stocks stock = f2.searchStock(content[2]);
                            TextMessage returnMsgF2_3 = session.createTextMessage();
                            returnMsgF2_3.setText(Double.toString(stock.getQuantity()));
                            publisher.send(returnMsgF2_3);
                        }
                        break;

                    default:
                        System.out.println("Wrong Method");
                        break;
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }

    }
}
