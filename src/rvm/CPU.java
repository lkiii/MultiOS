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
    private byte[] IP;
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
    Chan chn;
    //TODO hi-level processor

    public CPU() {
        IP = new byte[2];
        TIMER = new Timer(1);
        TI = 0;
        IOI = 0;
        SI = 0;
        PI = 0;
        chn =  = new Chan();
    }

    public boolean interruptCheck() {
        if ((PI + SI + IOI + TI) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
