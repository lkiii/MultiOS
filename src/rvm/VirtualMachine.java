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
    private VirtualMemory memory;
    private RM parent;
    private short ip = 0;
    private boolean c = false;
    private short cs = 0;
    private short ds = 0;
    private short ss = 0; 
    
    public VirtualMachine(VirtualMemory vm, RM rm) {
        memory = vm;
        parent = rm;
    }
    
    public void setCodeSeg(short addr) {
        cs = addr;
        ip = cs;
    }
    
    public void setDataSeg(short addr) {
        ds = addr;
    }
 
    public void setStackSeg(short addr) {
        ss = addr;
    }
    
    
    
    
}
