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
    
    private VirtualMemory mem;
    private RM realMachine;
    private Word R = new Word();
    private Byte[] CS = {0,0}; //TODO jo manau reik replace Byte[] į int ar pan. Arba kurt savo TwoByteRegister kur yra ++ ir dar ko reik
    private Byte[] DS = {0,0};
    private Byte[] SS = {0,0};
    private Byte[] IP = {0,0};
    private Byte[] SP = {0,0};
    private Byte SF = 0;
    
    
    
    public VirtualMachine(VirtualMemory mem) {
        this.mem = mem;
    }
    public void step(){
        mem.readWord((int)CS+(int)IP, SF);
        CS++;
    }
}
