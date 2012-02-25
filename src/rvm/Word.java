package rvm;

/**
 *
 * @author lukas
 */
public class Word {
    private static int wordSize = 4;
    private byte[] word;
    
    public Word(byte[] word) {
        if (word.length > 3 || word == null) {
            throw new IllegalArgumentException("OutOfBounds or null (word(byte[]) constructor)");
        } 
        this.word = new byte[wordSize];
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
