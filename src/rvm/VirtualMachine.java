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

    public VirtualMachine(RM realMachine, Byte[] registers, VirtualMemory memory) {
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
        printRegisters();
        System.out.println("addr " + (CS*0x10+IP));
        executeCommand(memory.readWord((int) (CS*0x10 + IP)));
        IP++;
        boolean interrupt = realMachine.interruptTest();
    }
    
    // MF flago realizacija, galejau parsinant ifint, cia pat
    // arba kiekienoj komandoj, bet padariau cia 
    private void executeCommand(Word op) {
        String opcode = op.toString().toUpperCase();
        switch (opcode) {
            case "HALT":
                //halt();
                break;
            case "PUSH":
                break;
            case "POP_":
                break;
            default:
                System.out.println("isparsintas arg: " + Integer.parseInt(opcode.substring(2, 4), 16));
                
                // cia toks tricksas, kad isparsinu kaip value, ir kaip reiksme is adreso, ir ifas pl flaga paduos kazkuri
                // bet ne visiems tinka value, pvz lr sr, tai kad ifu nenaudot suchimichinsiu 
                Word argAsValue = new Word(Integer.parseInt(opcode.substring(2, 4), 16));
                Word argFromMemory = new Word(memory.readWord(argAsValue).toInt());
                System.out.println("mem" + argFromMemory);
                
                Word arg;
                // tik kai kuriom komandom
                // kuriom reik reaguot i MemFlag, tos ima <arg>, jei ima tik is atminties, ar tik kaip reiksme, tada <argasval> ir ...
                // negrazi alternatyva kiekviename case kuriam reik ifa idet, arba kiekvienos komandos metode
                if (MF == true) {
                    arg = argFromMemory;
                } else {
                    arg = argAsValue;
                }
                
                switch (opcode.substring(0, 2)) {
                    case "AD": 
                        //memory.writeWord(new Word(0x30), arg);
                        add(arg);
                        break;
                    case "SB":
                        sub(arg);
                        break;
                    case "LR":
                        loadRegister(arg);
                        break;
                    case "SR":
                        saveRegister(arg);
                        break;
                    case "CM":
                        compare(arg);
                        break;
                    case "JP":
                        jump(arg);
                        break;
                    case "JE":
                        jumpIfEqual(arg);
                        break;
                    case "JL":
                        jumpIfLess(arg);
                        break;
                    case "JG":
                        jumpIfGreater(arg);
                        break;
                    case "PD":
                        putData(memory.readWord(arg));
                        break;
                    case "GD":
                        Word data = getData();
                        memory.writeWord(arg, getData());
                        break;
                    default:
                        throw new RuntimeException("halt");
                        //break;
                        
                }
        }



    }

    // operations
    private void add(Word value) { 
        R = new Word(R.toInt() + value.toInt());
    }
    
    private void sub(Word value) {
        R = new Word(R.toInt() - value.toInt());
    }
    
    private void loadRegister(Word addr) {
        memory.writeWord(DS, addr.toInt(), new Word(R.toInt()));
    }
        
    
    private void saveRegister(Word addr) {
        R = memory.readWord(DS, addr.toInt());
    }
    
    private void compare(Word value) {
        if (R.toInt() < value.toInt()) { // pagal doca value atmintyje, galima pdaryti flaga MemFlag: false tai ne memory, true memory address
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
        if (SP == SS) {
            SP = (short) (SS + 0x1F);
        } else {
            SP--;
        }
        memory.writeWord(SS, SP, R);
    }
    
    private void pop() {
        R = memory.readWord(SS, SP);
        if (SP == SS + 1F) {
            SP = SS;
        } else {
            SP++;
        }
        
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
        // temp
        //halted=true;
        throw new UnsupportedOperationException("Programa sėkmingai baigė darbą");
    }
    
    public boolean isHalted() {
        return halted;
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
        System.out.println("R: " + R.toInt());
        System.out.println("SF: " + SF);
        System.out.println("CS: " + CS);
        System.out.println("DS: " + DS);
        System.out.println("SS: " + SS);
        System.out.println("ES: " + ES);
        System.out.println("IP: " + IP);
        }
}


