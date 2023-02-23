/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybean;

import javax.ejb.Stateful;

/**
 *
 * @author DELL
 */
@Stateful
public class StatefulCounterBean implements StatefulCounterBeanRemote {
    int counter = 0;
    @Override
    public int Increment() {
        return ++counter;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
