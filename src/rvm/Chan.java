/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

import java.util.Scanner;

/**
 * Kanalų registro įrenginio klasė
 * @author ernestas
 */
public class Chan {
   // chan array + isAvailable = {return chan[index] , kai chan[i], 0<=i<=2 booleanai}
    
    
    private CPU cpu;

    /**
     * Kanalų įrenginio kūrimas. Visi free nes kūrimo metu nieks
     * nenaudoja nieko.
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
     * @return ar laisvas kanalas indeksu index
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
     * @return 
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
     * Kaip ir CH1
     * @param output 
     */
    public void useChan2(String output){
        cpu.setChanOccupied(2);
        System.out.print(output);
        cpu.setChanAvailable(2);
    }
}
