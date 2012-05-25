package MOS.Resources;

import MOS.Process;
import MOS.Process.ProcessState;
import java.util.ArrayList;

/**
 * NELIEST BLET UZMUSIU SU ATVIORTKE
 * @author ernestas
 */
public class ResourceDistributor {
    public static ArrayList<Resource> resources;

    public ResourceDistributor() {
        resources = new ArrayList<>();
    }

    /*
     * <<Kurti resursą>>
     * Parametrai perduodami kuriant resursą: nuoroda į _proceso_ kūrėją, 
     * resurso išorinis vardas. Resursas kūrimo metu  pridedamas prie bendro 
     * resursų sąrašo, pridedamas prie tėvo sukurtų resursų sąrašo, jam priskiriamas 
     * unikalus vidinis vardas, sukuriamas resurso elementų sąrašas ir sukuriamas 
     * laukiančių procesų sąrašas.
     */
    public void createResource(Process creator, String resourceName) {
        Resource resource = new Resource(creator, resourceName);
        resources.add(resource);
        creator.resources.add(resource);
        
    }
    
    // <<Prašyti resurso>>
    // Procesas iškvietęs šį primityvą yra blokuojamas 
    //ir įtraukiamas į to resurso laukiančių procesų sąrašą.
    public void requestResource(Process caller, Resource res) {
        caller.state = ProcessState.BLOCKED;
        res.processQueue.add(caller);
    }
    
    /*
     * <<Naikinti resursą>>
     * Resurso deskriptorius šalinamas iš bendro resursų ir jo tėvo sukurtų 
     * resursų sąrašų, naikinamas jo elementų sąrašas, atblokuojami procesai 
     * laukiantys šio resurso, vėliau naikinamas pats deskriptorius.
     */
    public void removeResource(Resource res) {
        res.getCreator().resources.remove(res); 
       
        for (Process i : res.processQueue) {
            if (i.state == ProcessState.BLOCKED) {
                i.state = ProcessState.READY;
            }
        }
            
        resources.remove(res);
    }

    
    /*
     * <<Atlaisvinti resursą>>
     * Resurso elementas yra perduodamas šiam primityvui kaip parametras 
     * ir yra pridedamas prie resurso elementų sąrašo.
     */
    public void freeResource(Resource res) {
        if (!res.isReusable()) {
            resources.remove(res);
        }
        res.setFree();
    }
}

//TODO: planeris (Paleisti bent tris VM)
//TODO: start stop
//TODO: naujų vm pridėjimas
//TODO: Emė turi užsikurti trys vm ir veikimo metu dar vieną pridėt