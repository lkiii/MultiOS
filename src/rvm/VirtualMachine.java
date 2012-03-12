package rvm;

import java.util.Scanner;
import rvm.Constants.PROCESS_STATUS;

/**
 *
 * @author ernestas
 */
public class VirtualMachine {
    
    private VirtualMemory memory; // virtuali atmintis
    private RM realMachine; // tevine masina
    
    private Word R = new Word(0x0); // Bendros paskirties registras
    
    // Segments
    private final short CS; // Code segment register
    private final short DS; // Data segment register
    private final short ES; // Extra segment register (params)
    private final short SS; // Stack segment register
    
    
    private short IP = 0; // Instruction pointer
    private short SP = 0; // Stack pointer
    private Byte SF = 0; // Status flag
    private boolean MF = false; //memory flag . jei true tai komandai paduodamas atminties adresas, jei false - gryna reiksme
    private PROCESS_STATUS status;
    private boolean haltReached = false;

    public VirtualMachine(RM realMachine, short[] registers, VirtualMemory memory) {
        DS = registers[0];
        CS = registers[1];
        SS = registers[2];
        ES = 0x0;
        this.realMachine = realMachine;
        this.memory = memory;
        status = PROCESS_STATUS.READY;
    }
    
    public void step() {
        System.out.println("==STEP==");
        executeCommand(memory.readWord((int) (CS + IP)));
        IP++;
        boolean interrupt = realMachine.interruptTest();
    }
    
    // MF flago realizacija, galejau parsinant ifint, cia pat
    // arba kiekienoj komandoj, bet padariau cia 
    private void executeCommand(Word op) {
        String opcode = op.toString().toUpperCase();
        System.out.println("Atliekamas " + opcode); 
        switch (opcode) {
            case "HALT":
                halt();
                break;
            case "PUSH":
                push();
                break;
            case "POP_":
                pop();
                break;
            default:
                Word argAsValue = new Word(Integer.parseInt(opcode.substring(2, 4), 16));
                Word argFromMemory = new Word(memory.readWord(argAsValue).toInt());
                Word arg = argFromMemory;
                Word value;
                switch (opcode.substring(0, 2)) {
                    case "AD": 
                        value = new Word(memory.readWord(DS + argAsValue.toInt()).toInt());
                        add(value);
                        break;
                    case "SB":
                        value = new Word(memory.readWord(DS + argAsValue.toInt()).toInt());
                        sub(value);
                        break;
                    case "LR":
                        System.out.println(DS + argAsValue.toInt());
                        System.out.println(memory.readWord(DS + argAsValue.toInt()));
                        loadRegister(new Word(DS + argAsValue.toInt()));
                        break;
                    case "SR":
                        saveRegister(new Word(DS + argAsValue.toInt()));
                        break;
                    case "CM":
                        compare(argFromMemory);
                        break;
                    case "JP":
                        jump(argAsValue);
                        break;
                    case "JE":
                        jumpIfEqual(argAsValue);
                        break;
                    case "JL":
                        jumpIfLess(argAsValue);
                        break;
                    case "JG":
                        jumpIfGreater(argAsValue);
                        break;
                    case "PD":
                        System.out.println("OUTPUT: " + memory.readWord(arg));
                        //putData(memory.readWord(arg));
                        break;
                    case "GD":
                        /*Word data = getData();
                        memory.writeWord(arg, getData());*/
                        
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown opcode: " + opcode);
                        
                }
    
        }
                    printRegisters();


    }

    // operations
    private void add(Word value) { 
        R = new Word(R.toInt() + value.toInt());
    }
    
    private void sub(Word value) {
        R = new Word(R.toInt() - value.toInt());
    }
    
    private void loadRegister(Word addr) {
       R = memory.readWord(DS, addr.toInt());

    }
        
    
    private void saveRegister(Word addr) {
        memory.writeWord(DS, addr.toInt(), new Word(R.toInt()));
    }
    
    private void compare(Word value) {
        if (R.toInt() < value.toInt()) {
            SF = 0;
        } else if (R.toInt() == value.toInt()) {
            SF = 1;
        } else {
            SF = 2;
        }
    }
    
    private void jump(Word nextInstr) {
        IP = (short) nextInstr.toInt();
    }
    
    private void jumpIfEqual(Word addr) {
        if (SF == 1) {
            IP = (short) addr.toInt();
        }
    }
    
    private void jumpIfLess(Word addr) {
        if (SF == 0) {
            IP = (short) addr.toInt();
        }  
    }
    
    private void jumpIfGreater(Word addr) {
        if (SF == 2) {
            IP = (short) addr.toInt();
        } 
    }
    
    private void push() {
        /*if (SP == SS) {
            SP = (short) (SS + 0x1F);
        } else {
            SP--;
        }*/
        memory.writeWord(SS+SP, R);
        System.out.println("SS: " + SS + " SP: " + SP + " irasem " + memory.readWord(SS+SP).toInt());
        SP++;
    }
    
    private void pop() {
        SP--;
        System.out.println("SS: " + SS + " SP: " + SP + " gaunam i R " + memory.readWord(SS+SP).toInt());
        R = memory.readWord(SS+SP);
        /*if (SP == SS + 1F) {
            SP = SS;
        } else {
            SP++;
        }*/
        
    }
    /*
    public void setCodeSeg(byte addr) {
        CS = addr;
        IP = CS;
    }

    public void setDataSeg(byte addr) {
        DS = addr;
    }

    public void setStackSeg(byte addr) {
        SS = addr;
    */

    private void halt() {
        haltReached = true;
    }
    
    public boolean isHalted() {
        return haltReached;
    }

    private void putData(Word value) {
        
    }

    private void getData(Word adress) {
        
    }
    
    public VirtualMemory getMemory() {
        return memory;
    }
    
    // komandu testui
    public void printRegisters() {
        System.out.print(" R: " + R.toInt());
        System.out.print(" SF: " + SF);
        System.out.print(" CS: " + CS);
        System.out.print(" DS: " + DS);
        System.out.print(" SS: " + SS);
        System.out.print(" SP: " + SP);
        System.out.print(" ES: " + ES);
        System.out.print(" IP: " + IP);
        System.out.println();
        
        System.out.print("STACK: ");
        for (int i=0; i<= SP; i++) {
           System.out.print(memory.readWord(SS + i).toInt() + "(" + memory.readWord(SS + i) + ")" + ","); 
        }
        System.out.println();
    }
}


