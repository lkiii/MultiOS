/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import rvm.RealMachine;
import rvm.VirtualMachine;

/**
 *
 * @author ernestas
 */
public abstract class Service extends Process {
    
    public Service(VirtualMachine machine, String name, ProcessState state) {
        super(machine, name, state);
    }
    
    public abstract void interrupt(Interrupt i);
    
    public boolean run() {
        doService();
        if (ProcessState.READY == state) {
            return true;
        } else {
            return false;
        }
    }
    
    public abstract void doService();
    
}
