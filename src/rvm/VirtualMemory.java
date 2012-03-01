/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

/**
 *
 * @author ernestas
 */

// KAS PASKIRSTO PAGING TABLE? AR KERNEL AR GRYNAI MEMORYJE MASYVAS VIRTALIU MEMU IR JIEM ADRESUS PER METODA DUOT, CJ PIRMAS

// cia realizuojam paginga, o gal atskira klase? 
public class VirtualMemory {
    Word pagingTableAddress;
    Memory realMemory; // nuoroda i realia atminti, nu nebent statini Memory padaryt, tada nereiks
    
    public VirtualMemory(Word PTR, Memory rMemory) {
        pagingTableAddress = PTR;
        realMemory = rMemory;
    }
    
    public Word readWord(int track, int word) {
        // gal bl vietoj word integer, nu aisku grazu, bet jau dapiso nx kad byteais saugom, biesina nx
        // is jo i int aritmetikai atlikt parsint negaliu normaliai, pisau ir skambinau.
        // galim nebent worde ne byte[] o integer ir laikyt, jei norisi word 
        
        return new Word(realMemory.readWord(getAbsoluteAddress(track, word)).getInt()); // kopija ar refas ?
    }
    
    public void writeWord(int track, int word, Word data) {
        realMemory.writeWord(getAbsoluteAddress(track, word), data);
    }
    
    public int getAbsoluteAddress(int track, int word) {
        return (pagingTableAddress.getInt()*10 + track)*10 + word;
    }
}
