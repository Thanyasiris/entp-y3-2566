/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmsprimeserver;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class TextListener implements MessageListener {
    private MessageProducer replyProducer;
    private Session session;
    
    public TextListener(Session session) {
              
        this.session = session;
        try {
            replyProducer = session.createProducer(null);
        } catch (JMSException ex) {
            Logger.getLogger(TextListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void onMessage(Message message) {
        TextMessage msg = null;

        try {
            if (message instanceof TextMessage) {
                msg = (TextMessage) message;
                System.out.println("Reading message: " + msg.getText() + " " + 
                        msg.getJMSCorrelationID());
                // set up the reply message 
                TextMessage response = session.createTextMessage(msg.getText()+" na"); 
                response.setJMSCorrelationID(msg.getJMSCorrelationID());
                System.out.println("sending message " + response.getText());
                replyProducer.send(message.getJMSReplyTo(), response);
            } else {
                System.err.println("Message is not a TextMessage");
            }
          
        } catch (JMSException e) {
            System.err.println("JMSException in onMessage(): " + e.toString());
        } catch (Throwable t) {
            System.err.println("Exception in onMessage():" + t.getMessage());
        }
        
    }
    
    private boolean isPrime(int n) {
        int i;
        for (i = 2; i*i <= n; i++) {
            if ((n % i) == 0) {
                return false;
            }
        }
        return true;
    }
}

