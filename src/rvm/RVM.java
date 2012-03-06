package rvm;
/**
 * 
 * VU MIF PS 1gr. 2012
 * @author Ernestas Prisakaru 
 * @author Lukas Ignatavicius
 * 
 */
public class RVM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Word w = new Word(0);
        
        System.out.println(w.toInt());
        System.out.println(w.toInt());
        System.out.println(w.toInt());
        System.out.println(w.toInt());
        RM rm = new RM();
        VirtualMachine vm = rm.startNewVM();
        vm.step();
    }
}
