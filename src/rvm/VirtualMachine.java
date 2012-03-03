/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

/**
 *
 * @author ernestas
 */
public class VirtualMachine {
    
    VirtualMemory mem;
    RM realMachine;
    CPU cpu;
    
    public VirtualMachine(VirtualMemory mem) {
        cpu = new CPU();
        this.mem = mem;
    }
}
