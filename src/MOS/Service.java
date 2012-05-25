package MOS;

import Hardware.VirtualMachine;


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
