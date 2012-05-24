package Hardware;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import rvm.Word;

/**
 * Kanalų registro įrenginio klasė
 * @author ernestas
 */
public class Channels {
    private CPU cpu;
    private Word[] buffer;
    private int bufferSize = 0;
    private Screen scr = new Screen();
    private HDD harddrive = new HDD();

    /**
     * Kanalų įrenginio kūrimas. Visu kanalu pradines busenos "available"
     */
    public Channels(CPU cpu) {
        this.cpu = cpu;
        cpu.setChanAvailable(1);
        cpu.setChanAvailable(2);
        cpu.setChanAvailable(3);
        buffer = new Word[5];
    }

    /**
     *
     * @param index kanalų įrenginio numeris
     * @return true, jei kanalas nurodytu numeriu laisvas
     */
    public boolean isAvailable(int index) {
        if (index <= 3 && index >= 0) {
            return cpu.isChanAvailable(index);
        } else {
            return false;
        }
    }


    // Is hdd p-l nurodyta filenamea paima source codea
    // supervizorine --> vartotojo
    public ArrayList<Word> XCHG(String fileName) {
        ArrayList<Word> jobSource = null;
        try {
            cpu.setChanOccupied(1);
            harddrive.open(fileName, "r");
            jobSource = harddrive.readAll();
        } catch (IOException ex) {
            System.out.println("XCHG ERR: " + ex.getMessage());
        } finally {
            harddrive.close();
            cpu.setChanAvailable(1);
        }
        return jobSource;
    }

    // Supevizorine -> Vartotojo
    public void XCHG() {
        cpu.setChanOccupied(2);
        scr.put(toPut);
        cpu.setChanAvailable(2);        
    }
    
    // supervizorine -> stdout
    public void putData(String data) {
        
    }
    
}
