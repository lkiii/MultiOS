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
        Byte[] segs = new Byte[3];//DS, CS, SS
        if (!args.isEmpty() && !args.trim().isEmpty()) {
            segs[0] = (Byte) (byte) (args.length() / BLOCK_SIZE); // DS
        } else {
            segs[0] = 0x0;
        }
        Scanner scanner;
        scanner = new Scanner(new FileInputStream(fileName));//new FileInputStream(fileName) galima bus keist į stringą
        String line;// = scanner.nextLine();
        boolean isCSSet = false;
        boolean isSSSet = false;
        Boolean writeToDS = null;
        int counter = 0;
        while (!isCSSet || !isSSSet) {
            line = scanner.nextLine();
            if (counter++ > 2) {
                break;
            }
            if ("DS".equals((line.substring(0, 2)).toUpperCase())) {
                isCSSet = true;
                segs[1] = (Byte) (byte) (segs[0] + Integer.parseInt(line.substring(2), 16));
            } else if ("CS".equals((line.substring(0, 2)).toUpperCase())) {
                isSSSet = true;
                segs[2] = (Byte) (byte) (segs[1] + Integer.parseInt(line.substring(2), 16));
            } else if (".DAT".equals(line.toUpperCase())) {
                writeToDS = true;
                break;
            } else if (".COD".equals(line.toUpperCase())) {
                writeToDS = false;
                break;
            }
        }
        if (!isCSSet) {
            segs[1] = (Byte) (byte) (segs[0] + DEFAULT_DS_SIZE);
        }
        if (!isSSSet) {
            segs[2] = (Byte) (byte) (segs[1] + DEFAULT_CS_SIZE);
        }
        if (segs[0] + segs[1] + segs[2] + STACK_SIZE <= MAX_BLOCKS_IN_VM) {
            VirtualMemory vm = alloc(segs[0] + segs[1] + segs[2] + STACK_SIZE);
            VMList.add(new VirtualMachine(this, segs, vm));

            int writingAddress;
            if (writeToDS == null) {
                line = scanner.nextLine();
                if (".DAT".equals(line.toUpperCase())) {
                    writeToDS = true;
                } else if (".COD".equals(line.toUpperCase())) {
                    writeToDS = false;
                } else {
                    new RuntimeException("Sūdinas failas");
                }
            }
            if (writeToDS) {
                writingAddress = segs[0] * 0x10;
            } else {
                writingAddress = segs[1] * 0x10;
            }
            while (scanner.hasNext()) {
                line = scanner.nextLine();
                if (writeToDS) {
                    try {
                        line = "" + Integer.parseInt(line);
                    } catch (NumberFormatException ex) {
                    }
                    if (".COD".equals(line.toUpperCase())) {
                        writeToDS = false;
                        writingAddress = segs[1] * 0x10;
                        continue;
                    }
                }
                vm.writeWord(writingAddress++, new Word(line));
                System.out.println("parašė: '" + line + "' adresu" + Integer.toHexString(writingAddress));
            }
            for (int i = 0; i < (segs[0] + segs[1] + segs[2] + STACK_SIZE) * 16; i++) {
                System.out.println(Integer.toHexString(i) + " " + vm.readWord(i));
            }

            return VMList.get(0);
        } else {
            //testavimui reikia kad returnintų vmą. Po to nereiks.
            return null;
        }
    }

    /**
     * TODO anlize strukturos source'o
     *
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
    public VirtualMemory alloc(int requiredBlocksNumber) {
        //TODO kad gaut PTR reikia kažkur saugot masyvą freeWords ar pan. Pasitarsim dėl šito. 
        int i = 0x0;
        Word ptr = new Word(i);
        while (requiredBlocksNumber > 0) {
            requiredBlocksNumber--;
            mem.writeWord(i, new Word(0x100 + i));
            System.out.println(Integer.toHexString(i) + " " + mem.readWord(i).toInt());
            i++;
        }
        VirtualMemory VMmemory = new VirtualMemory(ptr, mem);
        return VMmemory;
    }

    public void free(VirtualMemory memory) {
        //TODO padaryt PTR žodžius free kaip alloc atvirkštinis
    }
}
