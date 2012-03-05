package rvm;

import static rvm.Constants.*;
/**
 * 
 * VU MIF PS 1gr. 2012
 * @author Ernestas Prisakaru 
 * @author Lukas Ignatavicius
 * 
 */
public class Memory {
    private Word[] memory;
    //private boolean[] PageEntryAvailable;
    private int size;
    
    public Memory(int size) {
        this.size = size;
        memory = new Word[size + 1];
       /* for (int i=0; i < PT_SIZE; i++) {
            PageEntryAvailable[i] = true;      
        }*/
    }
   
    public int getSize() {
        return size;
    }
    
    /*public boolean isPageEntryAvailable(int index) {
        return PageEntryAvailable[index]; // outofbounds    
    } */
    
    public Word readWord(int absAddress) {
        // FIXME uz ribu iseina cia patikrinimas ar proce?
        return memory[absAddress];
    }
    
    public void writeWord(int absAddress, Word data) {
        memory[absAddress] = data;
    }
    
    public void writeWord(int absAddress, String data) {
        memory[absAddress] = new Word(data.substring(0, WORD_SIZE));
    }
    
    // apgalvot 
    public void fillZeroes() {
        Word empty = new Word("0000");
        for (int i=0x0; i <= getSize(); i++) {
            writeWord(i, empty);
        }
    }
    //irgi apgalvot
    public Word[] getWords(){
        return memory;
    }
}
