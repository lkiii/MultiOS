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
    private boolean CH1; // input
    private boolean CH2; // output
    private boolean CH3; // external storage
    //TODO hi-level processor
    //FIXME byte->short word->int

    public CPU() {
        IP = 0;
        TIMER = new Timer(1);
        TI = 0;
        IOI = 0;
        SI = 0;
        PI = 0;
    }

    public boolean interruptCheck() {
        if ((PI + SI + IOI + TI) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCH1Occupied() {
        return CH1;
    }

    public boolean isCH2Occupied() {
        return CH2;
    }

    public boolean isCH3Occupied() {
        return CH3;
    }
    public void freeCH1() {
        CH1 = false;
    }

    public void freeCH2() {
        CH2 = false;
    }

    public void freeCH3() {
        CH3 = false;
    }
    public void occupyCH1() {
        CH1 = true;
    }

    public void occupyCH2() {
        CH2 = true;
    }

    public void occupyCH3() {
        CH3 = true;
    }
}
