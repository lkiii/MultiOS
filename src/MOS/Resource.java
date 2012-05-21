package MOS;

import java.util.ArrayList;

/**
 *
 * @author ernestas
 */
public class Resource {
    //public static Hashtable<String, Process> registered = new Hashtable
    public static ArrayList<Resource> resources = new ArrayList<>();
    
    public int id;
    private int idCounter = 0;
    public boolean reusable;
    public boolean free;
    public Process creator;
    
    /*
1.	Kurti resursą. Resursus kuria tik procesas. 
Resurso kūrimo metu perduodami kaip parametrai: nuoroda į proceso kūrėją, resurso 
        išorinis vardas. Resursas kūrimo metu yra: pridedamas prie bendro resursų 
                sąrašo, pridedamas prie tėvo suskurtų resursų sąrašo, jam priskiriamas unikalus 
                        vidinis vardas, sukuriamas resurso elementų sąrašas ir sukuriamas laukiančių procesų sąrašas.*/
    public String name;
    /**
     * Kuria resursč
     * @param creator
     * @param name 
     */
    public Resource(Process creator, String name) {
        this.name = name;
        this.creator = creator;
        this.id = this.idCounter++;
        resources.add(this);
    }
    /**
     * atlaisvina resursą. Jį gali naudoti kitas.
     */
    public void freeResource(){
        this.free = true;
    }
    /**
     * tina resursą jeigu jis nėra reusable.
     */
    public void deleteResource(){
        if (!this.reusable){
            resources.remove(this);
        }
    }
    
    
    
    
}
