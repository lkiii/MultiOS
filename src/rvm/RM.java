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

    /**
     * Virtuali mašina be parametrų
     *
     * @param file programos failas
     * @return virtualią mašiną
     */
    public VirtualMachine startNewVM(File file) {
        return startNewVM(file, "");
    }

    public VirtualMachine startNewVM(File file, String args) {
        Byte[] segs = new Byte[3];
        if (!args.isEmpty() && !args.trim().isEmpty()) {
            segs[0] = (Byte) (byte) (args.length() / BLOCK_SIZE); // DS
        }else{
            segs[0] = 0x0;
        }
        segs[1] = 0x02;
        segs[2] = 0x05;
        if (segs[0] + segs[1] + segs[2] + STACK_SIZE <= MAX_BLOCKS_IN_VM) {
            VirtualMemory vm = getNewVirtualMemory();
            VMList.add(new VirtualMachine(this, segs, vm));
            // programa testine
            vm.writeWord(new Word(0x20), new Word("AD0A"));
            vm.writeWord(new Word(0x21), new Word("JP00"));
            vm.writeWord(new Word(0x22), new Word("JP00"));
            vm.writeWord(new Word(0x23), new Word("AD10"));
            return VMList.get(0);
        } else {
            //testavimui reikia kad returnintų vmą. Po to nereiks.
            return null;
        }
    }

    /*public static Byte[] load(Word[] source, VirtualMemory vm) {
        int ptr = 0;
        if (source[0].toString().toUpperCase() == ".DATA") {
            ptr++;
            while (source[ptr].toString().toUpperCase() != ".CODE") {
                if (source[ptr].toString().toUpperCase()) {
                }
            }
        }
        if (source[) {
        }
    }*/

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
    // alloc

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
