package MOS.Resources;

import MOS.Process;
import java.util.ArrayList;

/**
 *
 * @author ernestas
 */
public class Resource {

    public int id;
    public boolean reusable;
    public boolean free;
    public Process creator;
    Object element;
    public String name;
    
    /*
1.	Kurti resursą. Resursus kuria tik procesas. 
Resurso kūrimo metu perduodami kaip parametrai: nuoroda į proceso kūrėją, resurso 
        išorinis vardas. Resursas kūrimo metu yra: pridedamas prie bendro resursų 
                sąrašo, pridedamas prie tėvo suskurtų resursų sąrašo, jam priskiriamas unikalus 
                        vidinis vardas, sukuriamas resurso elementų sąrašas ir sukuriamas laukiančių procesų sąrašas.*/
    /**
     * Kuria resursč
     * @param creator
     * @param name 
     */
    public Resource(Process creator,int id,String name) {
        this.name = name;
        this.creator = creator;
        this.id = id;
    }

    public Object getElement() {
        return element;
    }

    public void setElement(Object element) {
        this.element = element;
    }
        
}
