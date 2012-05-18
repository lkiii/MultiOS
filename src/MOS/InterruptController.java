/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import MOS.Interrupt.InterruptType;
import java.util.ArrayList;
import java.util.Hashtable;
import rvm.RM;

/**
 *
 * @author ernestas
 */
public class InterruptController {
    private RM machine;
    private ArrayList<Interrupt> interrupts = new ArrayList<>();
    private Hashtable<InterruptType, Service> map = new Hashtable<>();
    
    public InterruptController(RM machine) {
        this.machine = machine;
    }
    
    public void interrupt(Interrupt interrupt) {
        this.interrupts.add(interrupt);
    }
    
    public Interrupt getInterrupt() {
        if (interrupts.isEmpty()) {
            return null;
        }
        
        Interrupt interrupt = interrupts.get(0);
        interrupts.remove(0);
        
        if (map.containsKey(interrupt.type)) {
            map.get(interrupt.type).interrupt(interrupt);
        } 
        
        return interrupt;
    }
    
    public boolean attachInterrupt(InterruptType type, Service service) {
        if (map.containsKey(type)) {
            return false;
        }
        
        map.put(type, service);
        return true;
    }
}

