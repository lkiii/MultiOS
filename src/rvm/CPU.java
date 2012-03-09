package rvm;

/**
 *
 * VU MIF PS 1gr. 2012
 *
 * @author Ernestas Prisakaru
 * @author Lukas Ignatavicius
 *
 */
public class CPU {
    // pt reg

    private Word PTR;
    // general purpose registers 
    private Word R1;
    private Word R2;
    // instruction pointer
    private short IP;
    // log. reg
    private boolean C;
    // user/supervisor mode
    private byte MODE;
    // timer
    private Timer TIMER; // -1 jei netrapint
    // interrupts
    private byte TI; //
    private byte IOI; // I/O 
    private byte SI; // 
    private byte PI; // 
    // channels
    private boolean[] CHN;
    //TODO hi-level processor

    public CPU() {
        IP = 0;
        TIMER = new Timer(1);
        TI = 0;
        IOI = 0;
        SI = 0;
        PI = 0;
        CHN = new boolean[3];
        for (boolean i: CHN) {
            i = true;
        }
    }

    public boolean interruptCheck() {
        if ((PI + SI + IOI + TI) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isChanAvailable(int index) {
        if (index <= 3 && index >= 0) {
            return CHN[index];
        }
        return false;
    }
    
    // apatinius metodus galima sutrukt i viena, antras argas b8t7 boolean status
    public void setChanAvailable(int index) {
        if (index <= 3 && index >= 0) {
            CHN[index] = true;
        }
    }

    public void setChanOccupied(int index) {
        if (index <= 3 && index >= 0) {
            CHN[index] = false;
        }
    }
}
