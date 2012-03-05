/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication25;
import java.util.Random;
/**
 *
 * @author ernestas
 */
public class JavaApplication25 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random gen = new Random();
        long suc = 0;
        for (long i=1; i<10000; i++) {
            double stf = gen.nextDouble()*8;
            double stf2 = gen.nextDouble()*8;
            //System.out.println("(" + stf2 + ", " + stf2 + ")");
            if (stf + stf2 <= 10 && stf +stf2 >= 8) {
                suc++;
            }
        }
        System.out.println(suc);
        System.out.println(suc/10000.0);
    }
}
