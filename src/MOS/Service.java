/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import rvm.RM;

/**
 *
 * @author ernestas
 */
public abstract class Service extends Process {
    
    public Service(RM machine, String name, ProcessState state) {
        super(machine, name, state);
    }
    
    public abstract void interrupt(Interrupt i);
    
    @Override
    public boolean run() {
        doService();
        if (ProcessState.READY == status) {
            return true;
        } else {
            return false;
        }
    }
    
    public abstract void doService();
    
}
