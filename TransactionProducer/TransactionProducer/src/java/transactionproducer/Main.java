/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transactionproducer;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author sarun
 */
public class Main {
    @Resource(mappedName = "jms/ConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/SimpleJMSQueue")
    private static Queue queue;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(true, 0);

            MessageProducer producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setText("Withdraw A 500");
            System.out.println("Sending message: " + message.getText());
            producer.send(message);
            int i = 1 / 0; //make error
            message.setText("Deposit B 500");
            System.out.println("Sending message: " + message.getText());
            producer.send(message);
            producer.send(session.createMessage()); //send empty message
            session.commit();            
            /*
             * Send a non-text control message indicating end of
             * messages.
             */
            //producer.send(session.createMessage());
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (JMSException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
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

