/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

/**
 *
 * @author root
 */
public class TwoByteRegister {
    
    private Byte[] register = new Byte[2];
    
    public TwoByteRegister(Byte[] value){
        this.register = value;
    }
    
    public void add(){
        add(1);
    }
    public void add(int x){
        
    }
    public int toInt(){
        return register[0]*0xf+register[1];
    }
}

