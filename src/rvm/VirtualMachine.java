package rvm;

/**
 *
 * @author ernestas
 */
public class VirtualMachine {

    private VirtualMemory memory;
    private RM realMachine;
    private Word R = new Word();
    private final Byte CS; //TODO jo manau reik replace Byte[] į int ar pan. Arba kurt savo TwoByteRegister kur yra ++ ir dar ko reik
    private final Byte DS;
    private final Byte SS;
    private short IP = 0;
    private short SP = 0;
    private Byte SF = 0;

    public VirtualMachine(VirtualMemory mem, Byte[] registers) {
        this.memory = mem;
        this.DS = registers[0];
        this.CS = registers[1];
        this.SS = registers[2];

    }

    public void step() {
        executeCommand(memory.readWord((int) CS + (int) IP, SF));
        IP++;
        realMachine.interruptCheck();
    }

    private void executeCommand(Word op) {
        String opcode = op.get().toString().toUpperCase();
        switch (opcode) {
            case "HALT":
                halt();

                break;
            case "PUSH":
                break;
            case "POP ":
                break;
            default:
                Word addr = new Word(opcode.substring(2, 4));
                
                switch (opcode.substring(0, 2)) {
                    case "AD": // ADD, AD D ar ADD?
                        add();
                        break;
                    case "SB":
                        break;
                    case "LR":
                        break;
                    case "SR":
                        break;
                    case "CM":
                        break;
                    case "JP":
                        break;
                    case "JE":
                        break;
                    case "JL":
                        break;
                    case "JG":
                        break;
                    case "PD":
                        break;
                    case "GD":
                        break;
                    default:
                        // interrupt. unknown opcode
                        break;
                        
                }
        }



    }

    // operations
    private void add(Word value) {
        R = new Word(R.getInt() + value.getInt());
    }
    
    private void sub(Word value) {
        R = new Word(R.getInt() - value.getInt());
    }
    
    private void loadRegister(Word addr) {
        memory.writeWord(DS, addr, new Word(R.getInt()));
    }
        
    
    private void saveRegister(Word addr) {
        R = memory.readWord(DS, addr);
    }
    
    private void compare(Word value) {
        
    }
    
    private void jump() {
        
    }
    
    private void jumpIfEqual() {
        
    }
    
    private void jumpIfLess() {
        
    }
    
    private void jumpIfGreater() {
        
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
