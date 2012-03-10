package rvm;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
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
        short DS = 0; 
        short CS = 0; 
        short ES = 0; 
        short SS = STACK_SIZE;
       
        DS = 0x00;
        
        CS = 0x02;
        SS = 0x05;
        int size = DS + CS + ES + STACK_SIZE;
        if (size <= MAX_BLOCKS_IN_VM) {
            VirtualMemory vm = alloc(size);
            VMList.add(new VirtualMachine(this, vm,{DS, CS, ES, SS}));
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
    public VirtualMemory alloc(int size) {
        Word newEntry = null;
        int newPTR = 0;
        for (int i=newPTR; i<size; i++) {
            int randomCell = (int) (Math.random() * 100);
            mem.writeWord(i, new Word(randomCell));
            System.out.println(i + " -> " + randomCell);
        }
        
        VirtualMemory VMmemory = new VirtualMemory(new Word(newPTR), mem);
        return VMmemory;
    }
    
    public void free(VirtualMemory memory) { 
    }

}                      
