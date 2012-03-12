package rvm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static rvm.Constants.*;
import sun.org.mozilla.javascript.internal.regexp.SubString;

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
        mem.fillZeroes();
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

    /**
     * Virtualios masinos paleidimas
     *
     * @param komandos komandu sarasas
     * @param args parametrai paleidziami programai
     * @return sugeneruota VM
     */
    public VirtualMachine startNewVM(String fileName, String args) throws FileNotFoundException {
        /*Byte[] segs = new Byte[3];//DS, CS, SS
        if (!args.isEmpty() && !args.trim().isEmpty()) {
            segs[0] = (Byte) (byte) (args.length() / BLOCK_SIZE); // DS
        } else {
            segs[0] = 0x0;
        }*/
        // 1budas
        // du ciklai vienu nustatom ar korektiska
        // kitu  jau zinom kur bus renkam duomenis
        // 2budas
        // viename cikle atpazystam ir renkam
        // cia 1 budas
        Scanner scanner;
        scanner = new Scanner(new FileInputStream(fileName));
        String line;
        boolean dataSegPresents = false;
        boolean codeSegPresents = false;
        boolean haltPresents = false;
        boolean illegalStructure = false;
        int counter = 0;
        int dataSegStart = -1;
        int codeSegStart = -1;
        
        // checkas segmentu korektiskumui
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            // segmentu isdestimo checkas
            if ((line.equals(".DATA") && dataSegPresents) || (line.equals(".CODE") && codeSegPresents) ||
                (line.equals("HALT") && !codeSegPresents)) {
                illegalStructure = true;
            } 
            
            if (line.equals(".DATA")) {
                dataSegPresents = true;
                dataSegStart = counter+1; // po .DATA duomenys
            }
            if (dataSegPresents && line.equals(".CODE")) { // jau yra data ir radom code
                codeSegPresents = true;
                codeSegStart = counter+1; // po .CODE komandos
            }
            if (codeSegPresents && line.equals("HALT")) { // jau yra code (tai ir data) ir radom halt
                haltPresents = true;
            }
            counter++;
        }
        
        System.out.println("DATASEG: " + dataSegPresents + dataSegStart);
        System.out.println("CODESEG: " + codeSegPresents + codeSegStart);
        System.out.println("HALT: " + haltPresents);
        
        // jau surinkimas atminties
        if (dataSegPresents && codeSegPresents && haltPresents && !illegalStructure) {
            System.out.println("Perkeliama i atminti");
            scanner = new Scanner(new FileInputStream(fileName));
            VirtualMemory vm = alloc(counter - 2 + 32); // dydis toks, koks failas minus .DATA ir minus .CODE zodzius
            counter = 0;
            int memCursorPosition = 0;
            boolean haltReached = false;
            
            short DS = 0;
            short CS = (short) (codeSegStart - dataSegStart - 1);
            short SS = (short) (DS + CS);
            
            while (scanner.hasNext() && !haltReached) {
                line = scanner.nextLine();
                System.out.println("parsinamas: " + line);
                
                // duomenu irasymas
                if (counter >= dataSegStart && counter < codeSegStart-1) { // counter yra tarp .data ... .code
                    if (line.startsWith("STR ")) {
                        System.out.println(" str [" + memCursorPosition + "] := " + line.substring(4, line.length()-1));
                        vm.writeWord(memCursorPosition, 
                                        new Word(line.substring(4, line.length()-1)));
                        memCursorPosition++;
                    } else {
                        System.out.println(" int [" + memCursorPosition + "] := " + Integer.parseInt(line));
                        vm.writeWord(memCursorPosition, new Word(Integer.parseInt(line)));
                        memCursorPosition++;
                    }
                } 
                
                // kodo irasymas
                if (counter >= codeSegStart) {
                    System.out.println("[" + memCursorPosition + "] := " + line);
                    vm.writeWord(memCursorPosition, new Word(line));
                    memCursorPosition++;
                }
                
                if (line.equals("HALT")) {
                    haltReached = true;
                }
                counter++;
            }
            SS = (short) memCursorPosition;
            
            
            System.out.println("left main loop, vmsize: " + vm.getSize() + " memcursor: " + memCursorPosition);
            for (int i=0; i<vm.getSize(); i++) {
                System.out.println("[" + i + "]" + vm.readWord(i));
            }

            short[] segs = {DS, CS, SS};
            VMList.add(new VirtualMachine(this, segs, vm));
            return VMList.get(0);
        } else {
            return null;
        }
    }

    public Word[] getAvailableBlocks(int blocks) {
        Word[] track = new Word[blocks * 0xf];
        return track;
    }

    /**
     * Neuzbaigtas
     *
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
    public VirtualMemory alloc(int requestedWords) {
        //TODO kad gaut PTR reikia kažkur saugot masyvą freeWords ar pan. Pasitarsim dėl šito. 
        int i = 0x0;
        Word ptr = new Word(i);
        int memSize = requestedWords/BLOCK_SIZE;
        while (requestedWords > 0) {
            requestedWords--;
            mem.writeWord(i, new Word(0x100 + i));
            i++;
        }

        VirtualMemory VMmemory = new VirtualMemory(ptr, mem);
        VMmemory.setSize(memSize);
        return VMmemory;
    }

    public void free(VirtualMemory memory) {
        //TODO padaryt PTR žodžius free kaip alloc atvirkštinis
    }
}
