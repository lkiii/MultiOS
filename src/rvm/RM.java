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
    
    public void startNewVM(String path, String args/* programos vardas ar kelias iki jo ir parametrai*/) {
        int numVM = VMList.size(); // page table.entr. addr. => size+1
        // max VM = 16 - pageTableSize - sharedMemorySize

        int requiredBlocks = 0xf;
        VirtualMemory VMmemory = new VirtualMemory(new Word(),mem); // Virtuali atmintis VM'ui TODO reik kažkaip sugeneruot paging table per getAvailableTrack(requiredBlocks) ar kaip?
        VMList.add(new VirtualMachine(VMmemory));

        // inicijuoti atminti
        //int pageTableAddress = getNewPageTableEntryAddress();
        // surusiuotas uzimtu tracku masyvas

        //int[] usedTracks = new int[numVM*0x10]; // iskiriam atminties tiek kiek masinu paleista (*16 zodziu is kiekvieno)
        //for (int i=0x0; i<= numVM*0xf; i++) {
       //     usedTracks[0x010 + i] = mem.readWord(0x010);

        Word[] usedTracks = new Word[VMList.size()*0x10]; // iskiriam atminties tiek kiek masinu paleista (*16 zodziu is kiekvieno)
        for (int i=0x0; i<= VMList.size()*0xf; i++) {
            usedTracks[0x010 + i] = mem.readWord(0x010);

            
        }
        
       // for (int i=0x0; i<=0xF; i++) {
            
        }
   //     VMList.add(new VirtualMachine());
  //  }
    
    // surinkti masyva uzimtu takeliu
    // is eiles begti per atminti ir tikrinti ar takas uzimtas jei ne tai duoti masinai
    // tai surinkti 16 takeliu

    //public getAvailableTrack() {

    // FIXME o ne dinaminė atmintis? Tiek blokų kiek reikia data ir code + 2 blokai steko?
    public Word[] getAvailableTrack(int blocks) {
        Word[] track = new Word[blocks * 0xf];
        return track;

    }
    
    

  //  public isAvailable(int Track) {
   //     for (int i=0x010; i<=0x0F0) {
   //         for ()
  //      }
   // }
    public boolean isAvailable(int Track) {
        for (int i=0x010; i<=0x0F0; i++) {
            //for ()
        }
        return true;
    }

    
  //  public int getNewPageTableEntryAddress() {
        // error jei virsitas limitas vmu

     //   return 0x000 + VMList.size()*0x10;
 //   }
    
    
//}

 //       return 0x000 + VMList.size()*0x10;
    }
        
//}
