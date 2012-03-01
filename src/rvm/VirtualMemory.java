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
        
        return new Word(realMemory.readWord(getAbsoluteAddress(track, word)).getString()); // kopija ar refas ?
    }
    
    public void writeWord(int track, int word, Word data) {
        realMemory.writeWord(getAbsoluteAddress(track, word), data);
    }
    
    public int getAbsoluteAddress(int track, int word) { // hex params
        Word w = realMemory.readWord(pagingTableAddress.getHex());
        //System.out.println("[[" + w.get(0) + " " + w.get(1) + " " + w.get(2) + " " );
       
        //System.out.println("abs addr " + realMemory.readWord(pagingTableAddress.getHex()).getHex());
        //System.out.println("[" + pagingTableAddress.getHex() + "]: " + (realMemory.readWord(pagingTableAddress.getHex())).getString());
        Word absoluteTrack = realMemory.readWord(pagingTableAddress.getHex() + track);
        int absoluteAddress = absoluteTrack.getHex() + word;
        return absoluteAddress;
    }
}
