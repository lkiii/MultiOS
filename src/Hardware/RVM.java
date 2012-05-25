package Hardware;

import MOS.OperatingSystem;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * VU MIF PS 1gr. 2012
 *
 * @author Ernestas Prisakaru
 * @author Lukas Ignatavicius
 *
 */
public class RVM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RealMachine rm = new RealMachine();
        OperatingSystem os = new OperatingSystem(rm);
        os.start();
    }
}
