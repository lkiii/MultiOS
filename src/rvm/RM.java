package rvm;

import java.util.ArrayList;
import static rvm.Constants.*;

/**
 *
 * @author ernestas
 */
public class RM {

    CPU cpu; // Centrinis procesorius
    Memory mem; // Reali atmintis
    ArrayList<VirtualMachine> VMList; // Virtualiu masinu sarasas

    /**
     * Sukuriama reali masina, inicijuojant procesoriu, atminti, ir VM sarasas
     */
    public RM() {
        cpu = new CPU();
        VMList = new ArrayList();
        mem = new Memory(Constants.MEMORY_SIZE);
    }

    /**
     * Virtuali mašina be parametrų
     *
     * @param file programos failas (musu FSe)
     * @return virtualią mašiną
     */
    /*public VirtualMachine startNewVM(File file) {
        return startNewVM(file, "");
    }*/

    /**
     * Virtualios masinos paleidimas
     * @param komandos komandu sarasas
     * @param args parametrai paleidziami programai
     * @return sugeneruota VM
     */
    public VirtualMachine startNewVM(String[] komandos, String args) {
        Byte[] segs = new Byte[3];
        if (!args.isEmpty() && !args.trim().isEmpty()) {
            segs[0] = (Byte) (byte) (args.length() / BLOCK_SIZE); // DS
        }else{
            segs[0] = 0x0;
        }
        segs[1] = 0x02;
        segs[2] = 0x05;
        if (segs[0] + segs[1] + segs[2] + STACK_SIZE <= MAX_BLOCKS_IN_VM) {
            VirtualMemory vm = alloc();
            VMList.add(new VirtualMachine(this, segs, vm));
            // programa testine
            int offs = 0;
            for (String i: komandos) {
                vm.writeWord(new Word(segs[1]*0x10 + offs), new Word(i));
                offs++;
            }
            // pabaiga
            return VMList.get(0);
        } else {
            //testavimui reikia kad returnintų vmą. Po to nereiks.
            return null;
        }
    }

    /**
     * TODO anlize strukturos source'o
     * @return komandu sarasas
     */
    public static Word[] Loader() {
        return null;
    }

    
    public Word[] getAvailableBlocks(int blocks) {
        Word[] track = new Word[blocks * 0xf];
        return track;
    }

    /**
     * Neuzbaigtas 
     * @param Track
     * @return 
     */
    public boolean isAvailable(int Track) {
        for (int i = 0x010; i <= 0x0F0; i++) {
            //for ()
        }
        return true;
    }

    /**
     * Pertraukimo ivykio patikrinimas
     * @return true - ivyko | false - neivyko
     */
    public boolean interruptTest() {
        return cpu.interruptTest();
    }

    /**
     * Iskiriama atmintis atmintis virtualiai masinai
     * @return virtuali atmintis
     */
    public VirtualMemory alloc() {
        mem.writeWord(0x000, new Word(0x150));
        mem.writeWord(0x001, new Word(0x120));
        mem.writeWord(0x002, new Word(0xe20));
        mem.writeWord(0x003, new Word(0x130));
        mem.writeWord(0x004, new Word(0x140));
        mem.writeWord(0x005, new Word(0xf50));
        mem.writeWord(0x006, new Word(0x1f0));
        mem.writeWord(0x007, new Word(0x1e0));
        VirtualMemory VMmemory = new VirtualMemory(new Word(0x0), mem);
        return VMmemory;
    }
    
    public void free(VirtualMemory memory) { 
        memory.
    }

}                      
