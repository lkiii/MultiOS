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
    int Size;

    public VirtualMemory(Word PTR, Memory rMemory) {
        pagingTableAddress = PTR;
        realMemory = rMemory;
    }
    // track nėra block?

    /**
     *         // gal bl vietoj word integer, nu aisku grazu, bet jau dapiso nx kad
     * byteais saugom, biesina nx // is jo i int aritmetikai atlikt parsint
     * negaliu normaliai, pisau ir skambinau. // galim nebent worde ne byte[] o
     * integer ir laikyt, jei norisi word
     *
     * @param track
     * @param word
     * @return
     */
    public Word readWord(int track, int word) {
        return new Word(realMemory.readWord(getAbsoluteAddress(track, word)).toString()); // kopija ar refas ?
    }

    /**
     *
     * @param adress
     * @return
     */
    public Word readWord(int adress) {
        return readWord(adress / 0xF, adress % 0xF);
    }

    public Word readWord(Word arg) {
        return readWord(arg.toInt());
    }

    public void writeWord(int track, int word, Word data) {
        realMemory.writeWord(getAbsoluteAddress(track, word), data);
    }

    public void writeWord(int adress, Word data) {
        realMemory.writeWord(getAbsoluteAddress(adress / 0xF, adress % 0xF), data);
    }

    public int getAbsoluteAddress(int track, int word) { // hex params
        Word w = realMemory.readWord(pagingTableAddress.toHex());
        //System.out.println("[[" + w.get(0) + " " + w.get(1) + " " + w.get(2) + " " );

        //System.out.println("abs addr " + realMemory.readWord(pagingTableAddress.toHex()).toHex());
        //System.out.println("[" + pagingTableAddress.toHex() + "]: " + (realMemory.readWord(pagingTableAddress.toHex())).toString());
        Word absoluteTrack = realMemory.readWord(pagingTableAddress.toHex() + track);
        int absoluteAddress = absoluteTrack.toHex() + word;
        return absoluteAddress;
    }
}
