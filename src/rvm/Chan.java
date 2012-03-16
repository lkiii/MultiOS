package rvm;

import java.util.Scanner;

/**
 * Kanalų registro įrenginio klasė
 * @author ernestas
 */
public class Chan {
    
    private CPU cpu;

    /**
     * Kanalų įrenginio kūrimas. Visu kanalu pradines busenos "available"
     */
    public Chan(CPU cpu) {
        this.cpu = cpu;
        cpu.setChanAvailable(1);
        cpu.setChanAvailable(2);
        cpu.setChanAvailable(3);
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

    /**
     * Kol nėra Interfeiso ir OS gal užteks tokio.
     * Kai bus interfeisas imsim iš imputo, kai os gal dar kažką
     * @return nuskaitytas ivedimas
     */
    public String useChan1() {
        cpu.setChanOccupied(1);
        String ret;
        try (Scanner scanIn = new Scanner(System.in)) {
            ret = scanIn.nextLine();
        }
        
        cpu.setChanAvailable(1);
        return ret;
    }
    /**
     * Isvedimas i antraji kanala
     * @param output duomenys isvedami i kanala
     */
    public void useChan2(String output){
        cpu.setChanOccupied(2);
        System.out.print("OUTPUT:" + output);
        cpu.setChanAvailable(2);
    }
}
