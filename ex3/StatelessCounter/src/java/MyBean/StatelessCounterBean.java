/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyBean;

import javax.ejb.Stateless;

/**
 *
 * @author DELL
 */
@Stateless
public class StatelessCounterBean implements StatelessCounterBeanRemote {
    int counter = 0;
    @Override
    public int increment() {
        return ++counter;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
