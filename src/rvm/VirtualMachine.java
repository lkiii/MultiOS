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