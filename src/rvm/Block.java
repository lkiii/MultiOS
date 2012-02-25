/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

/**
 *
 * @author lukas
 */
public class Block {
    private static int nWords = 0xF;
    Word[] block;
    
    public Block(Word[] words) {
        block = new Word[nWords];
        block = words; // bbs
    }
    
    public Word getWord(int n) {
        return block[n];
    }
    
    public Word[] getRawBlock() {
        return block;
    }
}
