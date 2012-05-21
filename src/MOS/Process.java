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
public class Process implements Comparable<Process> {

    public enum ProcessState {RUN, READY, READYS, BLOCK, BLOCKS};
    
    public int id;
    public String name; // siaip vardas
    public ProcessState state; // busena
    public byte priority; // prioritetas
    private VirtualMachine vm;
    
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
        this.state = status;
        this.id = _ID++;
        
    }
    
    public void step() {
        vm.step();
        // timeris --
    }
    
    /**
     * 
     * @param child
     * @return 
     */
    private boolean addChild(Process child) {
        if (child.parent != null)
            return false;
        
        childs.add(child);
        child.parent = this;
        return true;
    }
    
    private boolean removeChild(Process child) {
        if (childs.indexOf(child) == -1)
            return false;
        
        childs.remove(child);
        return true;
    }
    
    protected void addResource(Resource r) {
        resources.add(r);
        if (neededResources.isEmpty()) {
            
            if (ProcessState.BLOCK == state) 
                state = ProcessState.READY;
            
            if (ProcessState.BLOCKS == state) 
                state = ProcessState.READYS;
        }
        
        // runas
    }
    
    // kaip ir primityvai
    
    protected void killProcess() {
        // gal reik atsargiai kad referencu neliktu pas kitus,
        // kaip variantas resursams duoti kazkoki booleana ar gyvas ar ne

        for (Process i : childs) {
            i.killProcess();
        }
        
        resources.removeAll(resources); // pats save salina
        parent.childs.remove(this);
        // todo ismesti is masinos procesu saraso
        // naikinti resursus
        // naikinti pati deskriptoriu hz
    }
    
    protected void stopProcess() {
        // todo jei current tai tiesiog stabdytu pasiruosusiu neatsizvelgiant i nieka
        
        if (this.state == ProcessState.READY) {
            this.state = ProcessState.READYS;
        }
        
        if (this.state == ProcessState.BLOCK) {
            this.state = ProcessState.BLOCKS;
        }    
    }
    
    // Aktyvuoti procesa
    protected void resumeProcess() {
        if (this.state == ProcessState.READYS) {
            this.state = ProcessState.READY;
        }
        
        if (this.state == ProcessState.BLOCKS) {
            this.state = ProcessState.BLOCK;
        } 
    }    

    @Override
    public int compareTo(Process p2) {
        Process p1 = this;
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
