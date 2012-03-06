/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MemoryTest;
import rvm.Word;

/**
 *
 * @author root
 */
public class WordTEst {
    public static void main(String[] args) {
        Word stringas = new Word("bllaba");
        Word intas = new Word(0xFE);
        Word arrayus = new Word(new byte[] {0x60, 0x50, 0x40, 0x30});
        
        System.out.println("stringas-> " + stringas.toString());
        System.out.println("intas-> " + intas.toString());
        System.out.println("arrayus-> " + arrayus.toString());
    }
}
