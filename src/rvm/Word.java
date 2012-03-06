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
        this.word = ByteBuffer.allocate(WORD_SIZE);
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
     * @return byte array
     */
    public byte[] getByteArray(){
        byte[] byteArray = new byte[word.capacity()];
        word.get(byteArray);
        return byteArray;
    }
    /**
     * Gražina wordo baitą pagal indeksą
     *
     * @param i kurį baitą grąžint
     * @return baitą
     */
    public byte getByte(int i) {
        return word.get(i);
    }

    /**
     * Gražina wordo baitą pagal indeksą
     *
     * @param i kurį baitą grąžint
     * @return charą
     */
    public char getChar(int i) {
        return word.getChar(i);
    }

    /**
     * Gražina wordą taip kaip saugo kaip skaičių
     *
     * @return wordą kaip skaičius
     */
    public int toInt() {
        return word.getInt();
    }
    /**
     * 48 veda kaip 0
     * @return String as chars
     */
    public String toASCIIString() {
        return new String(getByteArray());
    }

    /**
     * Gražina wordą kaip stringą taip kaip guli atmintį
     * @return numeric string
     */
    @Override
    public String toString() {
        return new String(getByteArray());
    }
}
