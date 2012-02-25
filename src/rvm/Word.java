/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

/**
 *
 * @author lukas
 */
public class Word {
    private static int wordSize = 4;
    private byte[] word;
    public Word(){
        word = new byte[wordSize];
    }
    public Word(byte[] word){
        this();
        this.word = word;
    }
    public byte[] get(){
        return word;
    }
    public byte get(int i){
        return word[i];
    }
    public void set(byte[] word){
        this.word=word;
    }
}
