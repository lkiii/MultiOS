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
        // TODO : Grazins is listo tinkamiausia net jei jis negali dirbti del resursu trukumo.
        // SPRENDIMAS: Arba du listai stabdyti ir galintys dirbti, arba begti per pqueue ir paimti galinti veikti (comparatoriui cia)
        for (Process p : machine.processes) { 
            return machine.processes.poll();
        }
    }
    
}
