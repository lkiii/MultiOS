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

    private Word() {
        this.word = new byte[WORD_SIZE];
        for (int i = 0; i< WORD_SIZE; i++){
            word[i] = 0;
        }
    }

    public Word(String data) {
        this();
        for (int i = WORD_SIZE-1; i >= data.length(); i--) {
            this.word[i] = (byte)data.charAt(data.length()-i);
        }
    }
    
    public Word(int data) {
        this();
        for (int i = 0; i < data.length(); i++) {
            this.word[i] = (byte)data.charAt(i);
        }
    }
    
    public Word(byte[] word){
        this.word = word;
        fillZeros();
    }
    public byte[] get() {
        return word;
    }

    public byte get(int i) {
        return word[i];
    }

    public int toInt() {
        System.out.println(word[0] + " " + word[1] + " " +word[2] + " "+word[3]);
        int wordAsInteger = 0;
        for (int i = 0; i < WORD_SIZE; i++) {
            wordAsInteger += word[i] * Math.pow(10, WORD_SIZE-i);
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
        char ret[] = new char[WORD_SIZE];
        for (int i=0; i<WORD_SIZE; i++ ){
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
