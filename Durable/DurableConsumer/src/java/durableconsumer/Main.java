/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durableconsumer;

import java.io.IOException;
import java.io.InputStreamReader;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

public class Main {

    @Resource(lookup = "jms/DurableConnectionFactory")
    private static ConnectionFactory durableConnectionFactory;
    @Resource(lookup = "jms/DurableTopic")
    private static Topic topic;

    public static void main(String[] args) throws JMSException {
        Connection connection = null;
        Session session = null;
        Destination dest = null;
        MessageConsumer consumer = null;
        TextListener listener = null;
        TextMessage message = null;
        InputStreamReader inputStreamReader = null;
        char answer = '\0';

        /*
         * In a try-with-resources block, create context.
         * Create durable consumer, if it does not exist already.
         * Register message listener (TextListener).
         * Receive text messages from destination.
         * When all messages have been received, enter Q to quit.
         */
        try {
            connection = durableConnectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            consumer = session.createDurableSubscriber(topic, "MakeItLast");
            listener = new TextListener();
            consumer.setMessageListener((MessageListener) listener);
            connection.start();
            System.out.println(
                    "To end program, type Q or q, " + "then <return>");
            inputStreamReader = new InputStreamReader(System.in);

            while (!((answer == 'q') || (answer == 'Q'))) {
                try {
                    answer = (char) inputStreamReader.read();
                } catch (IOException e) {
                    System.err.println("I/O exception: " + e.toString());
                }
            }
            //consumer.close();
            //session.unsubscribe("MakeItLast");
        } catch (JMSException e) {
            System.err.println("Exception occurred: " + e.toString());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                }
            }
            
        }
    }
}
