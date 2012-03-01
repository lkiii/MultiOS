package rvm;

import java.math.BigInteger;
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
    
    public Word(int size) {
        this.word = new byte[size];
    }
    
    public Word(String data) {
        if (data.length() == WORD_SIZE) {
            this.word = data.substring(0, WORD_SIZE).getBytes();
        } else if (data.length() < WORD_SIZE) {
            this.word = data.substring(0).getBytes();
        } else {
            throw new RuntimeException("ERROR STRING LENGTH IS MORE THAN WORD_SIZE: " + data.length() + " wordsize " + WORD_SIZE);
        }
    }
    
    public byte[] get() {
        return word;
    }
    
    public byte get(int i) {
        return word[i];
    }
    
    public int getInt() {
        int wordAsInteger = 0;
        for (int i=0; i < WORD_SIZE; i++) {
            wordAsInteger += word[i]*Math.pow(10, i);
        }
        return wordAsInteger;
    }
    
    public String getString() {
        return new String(word);
    }

    public void set(byte[] word) {
        this.word = word;
    }
    
    // bl mes uzsipisim su tais hexais, dabar noriu bl pvz neigiama pavaryt, tai bl kaip sunis turim rasyt converteri ir pildyt ji
    // ir tolko tad jokio jei is int i musu hex, kur poto vistiek operuojama kaip int ir nu krc neargumentuotas bybys
    public void set(int word) {
        this.word = BigInteger.valueOf(word).toByteArray();
    }
}
