package rvm;

import java.math.BigInteger;
import static rvm.Constants.*;

/**
 *
 * VU MIF PS 1gr. 2012
 *
 * @author Ernestas Prisakaru
 * @author Lukas Ignatavicius
 *
 */
public class Word {

    private byte[] word;

    public Word() {
        this.word = new byte[WORD_SIZE];
    }

    public Word(String data) {
        word = data.getBytes();
        /*
         * if (data.length() == WORD_SIZE) { this.word = data.substring(0,
         * WORD_SIZE).getBytes(); } else if (data.length() < WORD_SIZE) {
         * this.word = data.substring(0).getBytes(); } else { throw new
         * RuntimeException("ERROR STRING LENGTH IS MORE THAN WORD_SIZE: " +
         * data.length() + " wordsize " + WORD_SIZE);
        }
         */
    }
    
    public Word(int data) {
        word = BigInteger.valueOf(data).toByteArray();
    }

    public byte[] get() {
        return word;
    }

    public byte get(int i) {
        return word[i];
    }

    public int toInt() {
        int wordAsInteger = 0;
        for (int i = 0; i < WORD_SIZE; i++) {
            wordAsInteger += word[i] * Math.pow(10, i);
        }
        return wordAsInteger;
    }

    public int toHex() {
        return Utils.Converter.hexString2decimal(this.toString());
    }
    /**
     * 
     * @return String as chars 
     */
    public String toCharString() {
        char ret[] = new char[4];
        for (int i=0; i<4; i++ ){
            ret[i] = (char)word[i];
        }
        return new String(ret);
    }
    /**
     * 
     * @return numeric string
     */
    @Override
    public String toString(){
        return new String(word);
    }
}
