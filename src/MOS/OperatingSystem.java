/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import MOS.Scheduling.Planner;
import Hardware.RealMachine;
import MOS.Process.ProcessState;
import MOS.Resources.ResourceDistributor;
import MOS.Services.StartStop;

/**
 *
 * @author ernestas
 */
public class OperatingSystem {
    RealMachine rm;
    Planner planner = new Planner();
    ResourceDistributor resourceDistributor = new ResourceDistributor();
    
    
    
    public OperatingSystem(RealMachine rm) {
        this.rm = rm;
    }
    // Lengvesniam darbui surasom statinius i arrayju
    // Procesai
    
    // Resursai
    
    // entry pointas OSo, paleidus masina
    public void start() {
        // 1. inicijuojami statiniai dalykai (virsuj jau inicijuota dalis)
        Planner.createProcess(null, new StartStop());
        Planner.run();
    }
}
