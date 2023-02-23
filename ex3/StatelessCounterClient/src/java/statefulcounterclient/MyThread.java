/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statefulcounterclient;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import mybean.StatefulCounterBeanRemote;

/**
 *
 * @author user
 */
public class MyThread extends Thread {
    StatefulCounterBeanRemote counterBean = lookupStatefulCounterBeanRemote();
    private int num;
    public MyThread(int i) {
        num = i;
    }
    @Override
    public void run() {
        Random r = new Random();
        try {
            sleep(r.nextInt(10));
        } catch (InterruptedException ex) {
            Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("In Thread " + num + " value of count is " + counterBean.Increment());
    }
    private StatefulCounterBeanRemote lookupStatefulCounterBeanRemote() {
        try {
            Context c = new InitialContext();
            return (StatefulCounterBeanRemote) c.lookup("java:comp/env/StatefulCounterBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
}
