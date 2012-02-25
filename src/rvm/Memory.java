/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

/**
 *
 * @author ernestas
 */
public class Memory {
    private int wordSize;
    private int pageSize; // pages vs size
    private int size;
    
    // string[]?
    private char[][] memory;
    
    public Memory(int memorySize, int wordSize, int pageSize) {
        this.size = memorySize;
        this.wordSize = wordSize;
        this.pageSize = pageSize;
        // todo :
        memory = new char[size/wordSize][wordSize];
    }
    
    // sukurt hex klase?
    // start / end ?
    // [page][]
    public String getBytes(int start, int length) {
        
    }
}
