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
    Word[] block;
    
    public Block() {
        block = new Word[Global.blockSize];
    }
    
    public Block(Word[] words) {
        //FIXME ifai
        block = new Word[Global.blockSize];
        block = words; // bbs
    }
    
    public Word getWord(int n) {
        return block[n];
    }
    
    public Word[] getBlock() {
        return block;
    }
    
    
}
