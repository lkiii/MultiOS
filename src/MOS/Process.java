/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import java.util.ArrayList;
import rvm.RM;
import rvm.VirtualMachine;

/**
 *
 * @author ernestas
 */
public class Process {
    public enum ProcessState {RUN, READY, READYS, BLOCK, BLOCKS};
    
    public int id;
    public String name; // siaip vardas
    public ProcessState status; // busena
    public byte priority; // prioritetas
    
    protected Process parent; // proceso tevas
    public ArrayList<Process> childs = new ArrayList<>(); // vaikiniai procesai
    public ArrayList<Resource> resources = new ArrayList<>(); // turimi resursai
    public ArrayList<Resource> neededResources = new ArrayList<>(); // laukiami resursai
    
    private static int _ID = 0;
    
    protected VirtualMachine machine;
    
    /*
     * 
     * •	Kurti procesą. Šiam primityvui perduodama nuoroda į jo tėvą, jo pradinė būsena, 
     * prioritetas, perduodamų elementų sąrašas ir išorinis vardas. Pačio primityvo viduje vyksta 
     * proceso kuriamasis darbas. Jis yra registruojamas bendrame procesų sąraše, tėvo-sūnų sąraše, 
     * skaičiuojamas vidinis identifikacijos numeris, sukuriamas jo vaikų procesų sąrašas (tuščias), 
     * sukurtų resursų sąrašas. 
     */
    public Process(VirtualMachine machine, String name, ProcessState status) {
        this.machine = machine;
        this.name = name;
        this.status = status;
        this.id = _ID++;
        
    }
    
    /**
     * 
     * @param child
     * @return 
     */
    protected boolean addChild(Process child) {
        if (child.parent != null)
            return false;
        
        childs.add(child);
        child.parent = this;
        return true;
    }
    
    protected boolean removeChild(Process child) {
        if (childs.indexOf(child) == -1)
            return false;
        
        childs.remove(child);
        return true;
    }
    
    protected void addResource(Resource r) {
        resources.add(r);
        if (neededResources.isEmpty()) {
            
            if (ProcessState.BLOCK == status) 
                status = ProcessState.READY;
            
            if (ProcessState.BLOCKS == status) 
                status = ProcessState.READYS;
        }
        
        // runas
    }
    
    
}
