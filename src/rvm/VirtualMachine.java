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
    
    private VirtualMemory mem;
    private RM realMachine;
    private Word R = new Word();
    private TwoByteRegister CS = new TwoByteRegister({0,0}); //TODO jo manau reik replace Byte[] į int ar pan. Arba kurt savo TwoByteRegister kur yra ++ ir dar ko reik
    private TwoByteRegister DS = new TwoByteRegister({0,0});
    private TwoByteRegister SS = new TwoByteRegister({0,0});
    private TwoByteRegister IP = new TwoByteRegister({0,0});
    private TwoByteRegister SP = new TwoByteRegister({0,0});
    private Byte SF = 0;
    
    
    
    public VirtualMachine(VirtualMemory mem) {
        this.mem = mem;
    }
    public void step(){
        executeCommand(mem.readWord((int)CS+(int)IP, SF));
        CS++;
        realMachine.interruptCheck();
    }
    private void executeCommand(Word command){
        
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
