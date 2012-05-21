/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import java.util.ArrayList;
import java.util.Collections;
import rvm.VirtualMachine;

/**
 *
 * @author ernestas
 */
public class ResourceDistributor extends Service{
    public static ArrayList<Process> blockedProcesses = new ArrayList<>(); // visi procesai
    public static ArrayList<Resource> resources = new ArrayList<>(); // visi resursai

    public ResourceDistributor(VirtualMachine machine, String name, ProcessState state) {
        super(machine, name, state);
        this.resources = Resource.resources;
    }

    public static Process getProcess() {
        return (Process) Collections.max(blockedProcesses);
    }

    @Override
    public void interrupt(Interrupt i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doService() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
