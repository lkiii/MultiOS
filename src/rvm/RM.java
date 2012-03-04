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
        // inicijuoti atminti
        int pageTableAddress = getNewPageTableEntryAddress();
        // surusiuotas uzimtu tracku masyvas
        Word[] usedTracks = new Word[VMList.size()*0x10]; // iskiriam atminties tiek kiek masinu paleista (*16 zodziu is kiekvieno)
        for (int i=0x0; i<= VMList.size()*0xf; i++) {
            usedTracks[0x010 + i] = mem.readWord(0x010);
            
        }
        
        for (int i=0x0; i<=0xF; i++) {
            
        }
    }
    
    // surinkti masyva uzimtu takeliu
    // is eiles begti per atminti ir tikrinti ar takas uzimtas jei ne tai duoti masinai
    // tai surinkti 16 takeliu
    // FIXME o ne dinaminė atmintis? Tiek blokų kiek reikia data ir code + 2 blokai steko?
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
    
    public int getNewPageTableEntryAddress() {
        // error jei virsitas limitas vmu
        return 0x000 + VMList.size()*0x10;
    }
    public boolean interruptCheck(){
        return cpu.interruptCheck();
    }    
    
}
