/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS.Services;

import MOS.Interrupt;
import MOS.Service;

/**
 *
 * @author lukas
 */
public class StartStop extends Service {
    public static final int defaultPriority = 100;
    public static final String[] childrenNames = {
        "Disk",
        "JCL",
        "MainProc",
        "Loader",
        "Interrupt",
        "Writer"
    };
    
    public StartStop() {
        super("StartStop", ProcessState.READY);
    }
    
    @Override
    public void interrupt(Interrupt i) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        System.out.println("StartStop is running");
        if (super.resources.contains(id))
    }
    
}
