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

    private CPU cpu;

    /**
     * Kanalų įrenginio kūrimas. Visi free nes kūrimo metu nieks
     * nenaudoja nieko.
     */
    public Chan(CPU cpu) {
        this.cpu = cpu;
        cpu.freeCH1();
        cpu.freeCH2();
        cpu.freeCH3();
    }

    /**
     *
     * @param index kanalų įrenginio numeris
     * @return ar laisvas kanalas indeksu index
     */
    public boolean isAvailable(int index) {
        if (index == 1){
            return !cpu.isCH1Occupied();
        }else if (index == 2){
            return !cpu.isCH2Occupied();
        }else if (index == 3){
            return !cpu.isCH3Occupied();
        }
        return false;
    }

    /**
     * Kol nėra Interfeiso ir OS gal užteks tokio.
     * Kai bus interfeisas imsim iš imputo, kai os gal dar kažką
     * @return 
     */
    public String useChan1() {
        cpu.occupyCH1();
        String ret;
        try (Scanner scanIn = new Scanner(System.in)) {
            ret = scanIn.nextLine();
        }
        cpu.freeCH1();
        return ret;
    }
    /**
     * Kaip ir CH1
     * @param output 
     */
    public void useChan2(String output){
        cpu.occupyCH2();
        System.out.print(output);
        cpu.freeCH2();
    }
}
