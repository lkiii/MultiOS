/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rvm;

/**
 *
 * @author ernestas
 */
public class Chan {
    private boolean[] chan = new boolean[3];
    
    public Chan() {
        chan[0] = true;  // input
        chan[1] = true;  // output
        chan[2] = true;  // external storage
    }
    
    public boolean isAvailable(int index) {
        return chan[index];
    }
    
    public void setAvailability(int index, boolean status) {
        chan[index] = status;
    }

}
