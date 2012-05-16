/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package MOS;

import java.util.ArrayList;

/**
 *
 * @author ernestas
 */
public class Process {
    public enum ProcessStatus {RUN, READY, READYS, BLOCK, BLOCKS};
    
    public int id;
    public String name; // siaip vardas
    public ProcessStatus status; // busena
    public byte priority; // prioritetas
    
    protected Process parent; // proceso tevas
    public ArrayList<Process> childs = new ArrayList<>(); // vaikiniai procesai
    public ArrayList<Resource> resources = new ArrayList<>(); // turimi resursai
    public ArrayList<Resource> neededResources = new ArrayList<>(); // laukiami resursai
    
    private static int _ID = 0;
    
    protected Machine machine;
    
    /*
     * 
     * •	Kurti procesą. Šiam primityvui perduodama nuoroda į jo tėvą, jo pradinė būsena, 
     * prioritetas, perduodamų elementų sąrašas ir išorinis vardas. Pačio primityvo viduje vyksta 
     * proceso kuriamasis darbas. Jis yra registruojamas bendrame procesų sąraše, tėvo-sūnų sąraše, 
     * skaičiuojamas vidinis identifikacijos numeris, sukuriamas jo vaikų procesų sąrašas (tuščias), 
     * sukurtų resursų sąrašas. 
     */
    public Process(Machine machine, String name, ProcessStatus status) {
        this.machine = machine;
        this.name = name;
        this.status = status;
        this.id = _ID++;
        
    }
    
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
            
            if (ProcessStatus.BLOCK == status) 
                status = ProcessStatus.READY;
            
            if (ProcessStatus.BLOCKS == status) 
                status = ProcessStatus.READYS;
        }
        
        // runas
    }
}
