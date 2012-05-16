package MOS;

import java.util.ArrayList;

/**
 *
 * @author ernestas
 */
public class Resource {
    //public static Hashtable<String, Process> registered = new Hashtable
    public static ArrayList<Resource> resources = new ArrayList<>();
    
    public int Id;
    public boolean Reusable;
    public Process creator;
    
    /*
1.	Kurti resursą. Resursus kuria tik procesas. 
Resurso kūrimo metu perduodami kaip parametrai: nuoroda į proceso kūrėją, resurso 
        išorinis vardas. Resursas kūrimo metu yra: pridedamas prie bendro resursų 
                sąrašo, pridedamas prie tėvo suskurtų resursų sąrašo, jam priskiriamas unikalus 
                        vidinis vardas, sukuriamas resurso elementų sąrašas ir sukuriamas laukiančių procesų sąrašas.*/
    public String name;
    
    public Resource(Process creator, String name) {
        this.name = name;
        this.creator = creator;
    }
    
    
}
