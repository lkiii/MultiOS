/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS.Services;

import MOS.Interrupt;
import MOS.Service;
import java.util.ArrayList;
import rvm.RM;
import rvm.VirtualMachine;
import rvm.Word;

/**
 *
 * @author ernestas
 */
public class GetData extends Service {
    private ArrayList<Word> buffer = new ArrayList<>();
    
    public GetData(VirtualMachine machine, String name, ProcessState state) {
        super(machine, name, state);
    }
    
    @Override
    public void interrupt(Interrupt i) {
        if (resources.isEmpty()) {

        }
    }

    @Override
    public void doService() {
        if (buffer.isEmpty()) {
            //machine.useChan1(name);
            state = ProcessState.BLOCK;
        } else {
            //Resource r = resources.remove(0);
            //r.
        }
    }
    
}
