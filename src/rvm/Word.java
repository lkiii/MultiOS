package rvm;

/**
 *
 * @author lukas
 */
public class Word {
    private byte[] word;
    
    public Word() {
        this.word = new byte[Global.wordSize];
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
