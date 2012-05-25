package Hardware;

import Hardware.Memory.Memory;
import Hardware.Memory.VirtualMemory;
import MOS.Process;
import Utils.Word;

/**
 *
 * @author ernestas
 */
public class RealMachine {
    CPU cpu; // Centrinis procesorius
    Memory mem; // Reali atmintis
    Process currentProcess;
    
    
    Channels ch;

    /**
     * Sukuriama reali masina, inicijuojant procesoriu, atminti, ir VM sarasas
     */
    public RealMachine() {
        cpu = new CPU();
        mem = new Memory(Constants.MEMORY_SIZE);
        
        ch = new Channels(cpu);
        //mem.fillZeroes();
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
        for (int i = 1; i < Constants.BLOCK_SIZE; i++) { // pt ilgiai yra block size dydzio
            Word currentWord = mem.readWord(i);
            if (currentWord == null || currentWord.toInt() == 0) {
                if (newPTR == null) {
                    newPTR = new Word(i * Constants.BLOCK_SIZE);
                }
            } else {
                memUsed += currentWord.toInt();
            }

        }

        // jei nera ptro, reiskias lentele pilna
        //

        //System.out.println("memused" + memUsed);
        //System.out.println("Memory left:" + (MEMORY_SIZE - PT_SIZE - memUsed));
        if (newPTR == null && (Constants.MEMORY_SIZE - Constants.PT_SIZE - memUsed < blocksRequired)) {
            return null;
        }

        int ptCursor = newPTR.toInt();
        int blocksAlloced = 0;
        // begam ir tikrinam kiekviena tracka
        for (int blockAddress = Constants.PT_SIZE; blockAddress < Constants.MEMORY_SIZE/Constants.BLOCK_SIZE; blockAddress++) {
            // begam vel per ilgius ir imam nenulinius
            boolean notUsed = true;
            for (int sizeTableIndex = 1; sizeTableIndex < Constants.BLOCK_SIZE; sizeTableIndex++) {
                int ptrStart = (sizeTableIndex) * Constants.BLOCK_SIZE;
                int ptrEnd = ptrStart + mem.readWord(sizeTableIndex).toInt();
                for (int word = ptrStart; word < ptrEnd; word++) {
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
            mem.writeWord(newPTR.toInt() / Constants.BLOCK_SIZE, new Word(blocksRequired));
        }

        VirtualMemory VMmemory = new VirtualMemory(newPTR, mem);
        VMmemory.setSize(memSize * Constants.BLOCK_SIZE);
        return VMmemory;
    }

    private void free(VirtualMemory vm) {
        mem.writeWord((vm.getPTR().toInt())/Constants.BLOCK_SIZE, new Word(0));
    }
    
    
}
