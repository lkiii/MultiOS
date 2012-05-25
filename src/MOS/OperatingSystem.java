/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import Hardware.RealMachine;
import MOS.Resources.ResourceDistributor;

/**
 *
 * @author ernestas
 */
public class OperatingSystem {
    RealMachine machine = new RealMachine();
    Planner planner = new Planner(machine);
    ResourceDistributor resourceDistributor = new ResourceDistributor();
    
    // Lengvesniam darbui surasom statinius i arrayju
    // Procesai
    
    // Resursai
    
    // entry pointas OSo, paleidus masina
    public void startStop() {
        // 1. inicijuojami statiniai dalykai (virsuj jau inicijuota dalis)
        planner
    }
}
