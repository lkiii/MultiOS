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
    private final Byte CS; //TODO jo manau reik replace Byte[] į int ar pan. Arba kurt savo TwoByteRegister kur yra ++ ir dar ko reik
    private final Byte DS;
    private final Byte SS;
    private short IP = 0;
    private short SP = 0;
    private Byte SF = 0;
    
    
    
    public VirtualMachine(VirtualMemory mem, Byte[] registers) {
        this.mem = mem;
        this.DS = registers[0];
        this.CS = registers[1];
        this.SS = registers[2];
        
    }
    public void step(){
        executeCommand(mem.readWord((int)CS+(int)IP, SF));
        IP++;
        realMachine.interruptCheck();
    }
    private void executeCommand(Word command){
        //TODO erniui
    }
}
