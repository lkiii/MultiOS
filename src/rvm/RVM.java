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
        RM rm = new RM();
        // keisti parametru padeti, musu AD pl adresa, tai DS kinta nuo parmetru skaiciaus, reiskis niekad tikslaus adreso nenurodysi
        VirtualMachine vm = rm.startNewVM({ "AD10", "", ""});
        
        vm.step();   
        vm.step();
        vm.step();
        vm.step();
    }
}
