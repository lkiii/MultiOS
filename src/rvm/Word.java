package rvm;

import java.math.BigInteger;
import java.nio.ByteBuffer;
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

    private ByteBuffer word;

    /**
     * Konstruktorius sukuria 0 užpildytą word
     */
    private Word() {
        this.word = ByteBuffer.allocateDirect(WORD_SIZE);
    }

    /**
     * Kosntruktorius kuria word iš byte[]
     *
     * @param word byte[] iš kurio bus generuojamas word
     */
    public Word(byte[] word) {
        this();
        this.word.put(word);
    }

    /**
     * Kosntruktorius kuria word iš Stringo. Reikšmės saugomos kaip ASCII
     *
     * @param data String iš kurio kuriamas word
     */
    public Word(String data) {
        this();
        if (data.length() > WORD_SIZE) {
            data = data.substring(data.length() - WORD_SIZE, data.length());
        }
        this.word.put(data.getBytes());
    }

    /**
     * Kosntruktorius kuria word iš into. Reikšmės saugomos kaip skaičiai
     *
     * @param data int iš kurio kuriamas word
     */
    public Word(int data) {
        this();
        this.word.putInt(data);
    }

    /**
     * Grąžina wordą kaip byte array taip kaip guli atmintį
     *
     * @return byte array
     */
    public byte[] toByteArray() {
        byte[] byteArray = new byte[WORD_SIZE];
        for (int i = 0; i < WORD_SIZE; i++) {
            byteArray[i] = word.get(i);
        }
        return byteArray;
    }

    /**
     * Gražina wordo baitą pagal indeksą
     *
     * @param i kurį baitą grąžint
     * @return baitą
     */
    public byte toByte(int i) {
        return word.get(i);
    }

    /**
     * Gražina wordo baitą pagal indeksą
     *
     * @param i kurį baitą grąžint
     * @return charą
     */
    public char toChar(int i) {
        return word.getChar(i);
    }

    /**
     * Gražina wordą taip kaip saugo kaip skaičių
     *
     * @return wordą kaip skaičius
     */
    public int toInt() {
        word.flip();
        int i = word.getInt();
        word.flip();
        return i;
    }

    /**
     * Gražina wordą kaip stringą taip kaip guli atmintį
     *
     * @return numeric string
     */
    @Override
    public String toString() {
        return new String(this.toByteArray());
    }
}
