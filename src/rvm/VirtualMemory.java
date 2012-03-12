package rvm;

/**
 * Virtuali atmintis nuslepia kreipimosi i atminti sudetinguma,
 * suteikia sistemai realizuoti puslapiavimo mechanizma, siekiant
 * netolygia atminti VM pateikti kaip tolygia
 * 
 * @author ernestas
 */

public class VirtualMemory {

    private Word pageTableAddress;
    private Memory realMemory; // nuoroda i realia atminti, nu nebent statini Memory padaryt, tada nereiks
    private int size;

    /**
     * @param PTR puslapiavimo lenteles iraso adresas
     * @param rMemory reali atmintis, i kurios kreipimosi sudetinguma nusleps si (virtuali)
     */
    public VirtualMemory(Word PTR, Memory rMemory) {
        pageTableAddress = PTR;
        realMemory = rMemory;
    }

    /**
     *       
     *
     * @param track logines atminties takelio numeris
     * @param word  logines atminties zodzio tame takelyje numeris
     * @return nuskaitytas is realios atminties zodis
     */
    public Word readWord(int track, int word) {
        return new Word(realMemory.readWord(getAbsoluteAddress(track, word)).toString()); // kopija ar refas ?
    }

    /**
     *
     * @param address "absoliutus" adresas Virtualios atzvilgiu (Track*10+Word)
     * @return nuskaitytas is realios atminties zodis
     */
    public Word readWord(int address) {
        return readWord(address / 0x10, address % 0x10);
    }

    /**
     *
     * @param address "absoliutus" adresas Virtualios atzvilgiu pateiktas Word tipu (Track*10+Word)
     * @return nuskaitytas is realios atminties zodis
     */
    public Word readWord(Word arg) {
        return readWord(arg.toInt());
    }

    /**
     *
     * @param track logines atminties takelis i kuria bus rasoma
     * @param word logines atminties nurodyto takelio zodzio eile
     * @param data zodis irasomas nurodytu adresu
     */
    public void writeWord(int track, int word, Word data) {
        realMemory.writeWord(getAbsoluteAddress(track, word), data);
    }

    /**
     *
     * @param address adresas i kuri bus irasoma
     * @param data zodis irasomas nurodytu adresu
     */
    public void writeWord(int address, Word data) {
        writeWord(address / 0x10, address % 0x10, data);
    }
    
    /**
     * 
     * @param track loginis adresas
     * @param data zodis irasomas nurodytu adresu
     */
    public void writeWord(Word adress, Word data) {
        writeWord(adress.toInt(), data);
    }

    /**
     * Pasinaudodamas PTR ir parametrais pateiktu loginiu adresu
     * formuoja fizini adresa
     * 
     * @param track takelis
     * @param word zodis
     * @return tikslus fizinis adresas atmintyje
     */
    public int getAbsoluteAddress(int track, int word) { // hex params
        Word absoluteTrack = realMemory.readWord(pageTableAddress.toInt() + track);
        int absoluteAddress = absoluteTrack.toInt() + word;
        return absoluteAddress;
    }
    
    //testui, nebent pergalvot kaip paduot size
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return size;
    }
    
    public Word getPTR() {
        return pageTableAddress;
    }
}
