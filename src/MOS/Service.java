package MOS;

import Hardware.VirtualMachine;

/**
 *
 * @author ernestas
 */
public abstract class Service extends Process {

    public Service(String name, ProcessState state) {
        super(name, state);
    }

    public abstract void interrupt(Interrupt i);
}
