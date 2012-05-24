package rvm;

import MOS.Process;
import MOS.ProcessPriorityComparator;
import java.io.FileNotFoundException;
import static rvm.Constants.*;
import Tests.MemoryTest.*;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author ernestas
 */
public class RealMachine {
    
    CPU cpu; // Centrinis procesorius
    Memory mem; // Reali atmintis
    public PriorityQueue<MOS.Process> processes; 
    Process currentProcess;
    
    
    Chan ch;

    /**
     * Sukuriama reali masina, inicijuojant procesoriu, atminti, ir VM sarasas
     */
    public RealMachine() {
        cpu = new CPU();
        mem = new Memory(Constants.MEMORY_SIZE);
        
        ch = new Chan(cpu);
        //mem.fillZeroes();
        
        Comparator comparator = new ProcessPriorityComparator();
        processes = new PriorityQueue<>(20, comparator);
        
        processes.add(new StartStop());
    }

    /**
     * Virtuali mašina be parametrų
     *
     * @param file programos failas (musu FSe)
     * @return virtualią mašiną
     */
    public VirtualMachine startNewVM(String fileName) throws FileNotFoundException {
        return startNewVM(fileName, "");
    }

    public Word[] getAvailableBlocks(int blocks) {
        Word[] track = new Word[blocks * 0xf];
        return track;
    }

    /**
     * Pertraukimo ivykio patikrinimas
     *
     * @return true - ivyko | false - neivyko
     */
    public boolean interruptTest() {
        return cpu.interruptTest();
    }

    /**
     * Iskiriama atmintis atmintis virtualiai masinai
     *
     * @return virtuali atmintis
     */
    public VirtualMemory alloc(int blocksRequired) {

        // pirmiausia patikrinam ar yra laisvas blokas page teble entriui, 
        // teigiam kad pilnas, ir jei bent viena randam 0 ilgio reiskias nepilnas
        //boolean  = true;
        Word newPTR = null;
        int memSize = blocksRequired;
        int memUsed = 0;
        for (int i = 1; i < BLOCK_SIZE; i++) { // pt ilgiai yra block size dydzio
            Word currentWord = mem.readWord(i);
            if (currentWord == null || currentWord.toInt() == 0) {
                if (newPTR == null) {
                    newPTR = new Word(i * BLOCK_SIZE);
                }
            } else {
                memUsed += currentWord.toInt();
            }

        }

        // jei nera ptro, reiskias lentele pilna
        //

        //System.out.println("memused" + memUsed);
        //System.out.println("Memory left:" + (MEMORY_SIZE - PT_SIZE - memUsed));
        if (newPTR == null && (MEMORY_SIZE - PT_SIZE - memUsed < blocksRequired)) {
            return null;
        }

        int ptCursor = newPTR.toInt();
        int blocksAlloced = 0;
        // begam ir tikrinam kiekviena tracka
        for (int blockAddress = PT_SIZE; blockAddress < MEMORY_SIZE/BLOCK_SIZE; blockAddress++) {
            // begam vel per ilgius ir imam nenulinius
            //System.out.println("current blockAddress: " + blockAddress);
            boolean notUsed = true;
            for (int sizeTableIndex = 1; sizeTableIndex < BLOCK_SIZE; sizeTableIndex++) {
                //System.out.println("current SizeTableIndex: " + sizeTableIndex);
                // ptro wordas
                //TODO refactorint
                int ptrStart = (sizeTableIndex) * BLOCK_SIZE;
                int ptrEnd = ptrStart + mem.readWord(sizeTableIndex).toInt();
                //System.out.println("strt: " + ptrStart + " end: " + ptrEnd + " index" + mem.readWord(sizeTableIndex).toInt());
                for (int word = ptrStart; word < ptrEnd; word++) {
                    //System.out.println("foriukas");
                    if (blockAddress == mem.readWord(word).toInt()) {
                        notUsed = false;
                        break;
                    }
                }
                if (!notUsed) {
                    break;
                }
            }
            if (blocksAlloced >= blocksRequired) {
                break;
            }

            if (notUsed) {
                mem.writeWord(ptCursor, new Word(blockAddress));
                ptCursor++;
                blocksAlloced++;
            }
        }

        if (blocksAlloced >= blocksRequired) {
            mem.writeWord(newPTR.toInt() / BLOCK_SIZE, new Word(blocksRequired));
        }

        VirtualMemory VMmemory = new VirtualMemory(newPTR, mem);
        VMmemory.setSize(memSize * BLOCK_SIZE);
        return VMmemory;
    }

    private void free(VirtualMemory vm) {
        mem.writeWord((vm.getPTR().toInt())/BLOCK_SIZE, new Word(0));
    }
    
    public Word[] getMemoryGui() {
        return mem.getWords();
    }
    
    public Chan getChans(){
        return ch;
    };
}
