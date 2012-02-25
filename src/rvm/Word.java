package rvm;

/**
 *
 * @author lukas
 */
public class Word {
    private static int wordSize = 4;
    private byte[] word;
    
    public Word() {
        this.word = new byte[wordSize];
    }
    public Word(byte[] word) {
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
