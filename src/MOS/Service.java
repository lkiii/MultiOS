/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import rvm.RM;

/**
 *
 * @author ernestas
 */
public class Service extends Process {
    
    public Service(RM machine, String name, ProcessState state) {
        super(machine, name, state);
    }
    
}
