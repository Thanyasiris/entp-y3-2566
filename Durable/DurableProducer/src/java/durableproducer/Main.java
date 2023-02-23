/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package durableproducer;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

/**
 *
 * @author sarun
 */
public class Main {
    @Resource(mappedName = "jms/DurableTopic")
    private static Topic simpleJMSTopic;
    @Resource(mappedName = "jms/DurableConnectionFactory")
    private static ConnectionFactory connectionFactory;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Connection connection = null;
        //Destination dest = null;
        Main scoreObj = new Main(); 
        Scanner inp = new Scanner(System.in);
        String scoreStr = null;
        while(true) {
            System.out.print("Enter Message ");
            scoreStr = inp.nextLine();
            if (!scoreStr.equals("")) {
                try {
                    scoreObj.sendJMSMessageToSimpleJMSTopic(scoreStr);
                } catch (JMSException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                break;
            }
        }
    }

    private Message createJMSMessageForjmsSimpleJMSTopic(Session session, Object messageData) throws JMSException {
        // TODO create and populate message to send
        TextMessage tm = session.createTextMessage();
        tm.setText(messageData.toString());
        return tm;
    }

    private void sendJMSMessageToSimpleJMSTopic(Object messageData) throws JMSException {
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(simpleJMSTopic);
            messageProducer.send(createJMSMessageForjmsSimpleJMSTopic(session, messageData));
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Cannot close session", e);
                }
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
    
}

