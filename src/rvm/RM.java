/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

import java.util.ArrayList;
import static rvm.Constants.*;

/**
 *
 * @author ernestas
 */
public class RM {

    CPU cpu;
    Memory mem;
    ArrayList<VirtualMachine> VMList;

    public RM() {
        cpu = new CPU();
        VMList = new ArrayList();
        mem = new Memory(0xFFF);
    }

    //public void startNewVM(/*
    public VirtualMachine startNewVM(/*
             * programos vardas ar kelias iki jo ir parametrai
             */) {
        //int numVM = VMList.size(); // page table.entr. addr. => size+1
        //int requiredBlocks = 0xf;
        VMList.add(new VirtualMachine(this, new Byte[]{0x00, 0x30, 0x50})); // {DS,CS,SS}
        return VMList.get(0);
    }

    public Word[] getAvailableBlocks(int blocks) {
        Word[] track = new Word[blocks * 0xf];
        return track;
    }

    public boolean isAvailable(int Track) {
        for (int i = 0x010; i <= 0x0F0; i++) {
            //for ()
        }
        return true;
    }

    public boolean interruptCheck() {
        return cpu.interruptCheck();
    }

    public VirtualMemory getNewVirtualMemory() {
        mem.writeWord(0x000, new Word(0x150));
        mem.writeWord(0x001, new Word(0x120));
        mem.writeWord(0x002, new Word(0xe20));
        mem.writeWord(0x003, new Word(0x130));
        mem.writeWord(0x004, new Word(0x140));
        mem.writeWord(0x005, new Word(0xf50));
        mem.writeWord(0x006, new Word(0x1f0));
        VirtualMemory VMmemory = new VirtualMemory(new Word(0x0), mem);
        return VMmemory;
    }
    
        //TODO reik krc gi programoj atsizvelgt .DATA .CODE sintaksinius dalykus
    private boolean artvarkabendraisuprograma() {
        //int segment = 
        return true;
    }
}
