package rvm;

import static rvm.Constants.*;

/**
 * 
 * VU MIF PS 1gr. 2012
 * @author Ernestas Prisakaru 
 * @author Lukas Ignatavicius
 * 
 */
public class Word {
    private byte[] word;
    
    public Word() {
        this.word = new byte[WORD_SIZE];
    }
    public Word(byte[] word) {
        //FIXME ifai
        this();
        this.word = word;
    }
    
    public byte[] get() {
        return word;
    }
    
    public byte get(int i) {
        return word[i];
    }

    public void set(byte[] word) {
        this.word = word;
    }
}
