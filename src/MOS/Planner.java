package MOS;

import rvm.RM;

/**
 * Realizuotas per PriorityQueue (nesynchronized) ir Comparatoriu (Realiai jame ir yra palyginimo logika)
 * Alternatyva:
 *   ArrayList
 *   foreach'as ieskantis geriausio
 * @author ernestas
 */
public class Planner { 
    private RM machine;
    
    /**
     * Constructs the Planner for RM.
     * @param machine used to reach processes of RM.
     */
    protected Planner(RM machine) {
        this.machine = machine;
    }
    
    /**
     * Returns Process to be executed next.
     * @return highest priority process
     */
    public Process getNextProcess() {
        return machine.processes.poll();
    }
    
}
