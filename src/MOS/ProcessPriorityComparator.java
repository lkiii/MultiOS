package MOS;

import java.util.Comparator;

/**
 * Palygina processus. Pirmiausia pagal tai ar servicai ar procesai.
 * Jei vienodi, tada pagal prioriteta. Siame projekte realiai jis naudojamas 
 * prioritetineje eileje, kad palaikyti PSEUDO-tvarka (vis delto pqueue isbarsto heape)
 * @author ernestas
 */
public class ProcessPriorityComparator implements Comparator<Process> {
    
    @Override
    public int compare(Process p1, Process p2) {
        if (p1 instanceof Service && p2 instanceof Process) {
            return 1;
        }
        
        if (p1 instanceof Process && p2 instanceof Service) {
            return -1;
        }
        
        // If processes are both -> Services or Processes
        
        if (p1.priority > p2.priority) {
            return 1;
        } else {
            
            if (p1.priority < p2.priority) 
                return -1;
              else 
                // Given processes have equal priorities;
                return 0;
        }
    }
    
}
