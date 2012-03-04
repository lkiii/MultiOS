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
    
    public static void initialize() {
    
    }
    
    public void startNewVM(/* programos vardas ar kelias iki jo ir parametrai*/) {
        //TODO Luikui
        int numVM = VMList.size(); // page table.entr. addr. => size+1
        // max VM = 16 - pageTableSize - 
        int requiredBlocks = 0xf;
        VirtualMemory VMmemory = new VirtualMemory(new Word(),mem); // Virtuali atmintis VM'ui TODO reik kažkaip sugeneruot paging table per getAvailableTrack(requiredBlocks) ar kaip?
        VMList.add(new VirtualMachine(VMmemory,new Byte[]{0,0,0}));
        }
    public Word[] getAvailableBlocks(int blocks) {
        Word[] track = new Word[blocks * 0xf];
        return track;
    }
    
    
    public boolean isAvailable(int Track) {
        for (int i=0x010; i<=0x0F0; i++) {
            //for ()
        }
        return true;
    }
    public boolean interruptCheck(){
        return cpu.interruptCheck();
    }    
    
}
