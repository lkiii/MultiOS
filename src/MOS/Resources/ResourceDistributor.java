/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS.Resources;

import MOS.Interrupt;
import MOS.Process;
import MOS.Process.ProcessState;
import MOS.ProcessPriorityComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import rvm.VirtualMachine;

/**
 *
 * @author ernestas
 */
public class ResourceDistributor {

    private static int idCounter = 0;
    static Comparator comparator = new ProcessPriorityComparator();
    public static PriorityQueue<Process> blockedProcesses = new PriorityQueue<>(0, comparator); // visi procesai
    public static ArrayList<Resource> resources = new ArrayList<>(); // visi resursai

    public ResourceDistributor() {
    }

    public void createResource(){
        
    }
    private void deleteResource(Resource res) {
    }

    public void freeResource(Resource res) {
        if (!res.reusable) {
            resources.remove(res);
        }
        this.free = true;
    }
}
//TODO: planeris (Paleisti bent tris VM)
//TODO: start stop
//TODO: naujų vm pridėjimas
//TODO: Emė turi užsikurti trys vm ir veikimo metu dar vieną pridėt