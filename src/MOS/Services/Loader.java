/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS.Services;

import MOS.Interrupt;
import MOS.Service;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import rvm.*;

/**
 *
 * @author ernestas
 */
class Loader extends Service {
    
    public Loader(VirtualMachine machine, String name, ProcessState state) {
        super(machine, name, state);
    }
    
    private boolean load(String fileName) {

        Scanner scanner;
        try {
            scanner = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException ex) {
            System.out.println("FILEERR");
        }
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
            if ((line.equals(".DATA") && dataSegPresents) || (line.equals(".CODE") && codeSegPresents)
                    || (line.equals("HALT") && !codeSegPresents)) {
                illegalStructure = true;
            }

            if (line.equals(".DATA")) {
                dataSegPresents = true;
                dataSegStart = counter + 1; // po .DATA duomenys
                counter++;
                continue;
            }
            if (dataSegPresents && line.equals(".CODE")) { // jau yra data ir radom code
                codeSegPresents = true;
                codeSegStart = counter + 1; // po .CODE komandos
                counter++;
                continue;
            }
            if (codeSegPresents && line.equals("HALT")) { // jau yra code (tai ir data) ir radom halt
                haltPresents = true;
            }
            counter++;
        }
        


        //System.out.println("DATASEG: " + dataSegPresents + dataSegStart);
        //System.out.println("CODESEG: " + codeSegPresents + codeSegStart);
        //System.out.println("HALT: " + haltPresents);

        // jau surinkimas atminties
        if (dataSegPresents && codeSegPresents && haltPresents && !illegalStructure) {
            //System.out.println("Perkeliama i atminti");
            scanner = new Scanner(new FileInputStream(fileName));
            // apskaiciavimas reikiamu bloku
            int requiredBlocks = (counter % Constants.BLOCK_SIZE > 0) ? (counter / Constants.BLOCK_SIZE + 1) : (counter / Constants.BLOCK_SIZE);
            requiredBlocks += Constants.STACK_SIZE;
            VirtualMemory vm = machine.alloc(requiredBlocks); // dydis toks, koks failas minus .DATA ir minus .CODE zodzius
            counter = 0;
            int memCursorPosition = 0;
            boolean haltReached = false;

            short DS = 0;
            short CS = (short) (codeSegStart - dataSegStart - 1);
            short SS;

            while (scanner.hasNext() && !haltReached) {
                line = scanner.nextLine();
                //System.out.println("parsinamas: " + line);

                // duomenu irasymas
                if (counter >= dataSegStart && counter < codeSegStart - 1) { // counter yra tarp .data ... .code
                    if (line.startsWith("STR ")) {
                    //    System.out.println(" str [" + memCursorPosition + "] := " + line.substring(4));
                        vm.writeWord(memCursorPosition, new Word(line.substring(4)));
                        memCursorPosition++;
                    } else {
                    //    System.out.println(" int [" + memCursorPosition + "] := " + Integer.parseInt(line));
                        vm.writeWord(memCursorPosition, new Word(Integer.parseInt(line)));
                        memCursorPosition++;
                    }
                }

                // kodo irasymas
                if (counter >= codeSegStart) {
                    //System.out.println("[" + memCursorPosition + "] := " + line);
                    vm.writeWord(memCursorPosition, new Word(line));
                    memCursorPosition++;
                }

                if (line.equals("HALT")) {
                    haltReached = true;
                }
                counter++;
                //print(mem,false);
            }
            SS = (short) memCursorPosition;

            
            //System.out.println("left main loop, vmsize: " + vm.getSize() + " memcursor: " + memCursorPosition);
            for (int i = 0; i < vm.getSize(); i++) {
            //    System.out.println("[" + i + "]" + vm.readWord(i));
            }

            short[] segs = {DS, CS, SS};
            machine.VMList.add(new VirtualMachine(this, segs, vm));
            return VMList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void interrupt(Interrupt i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void doService() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
