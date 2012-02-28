package rvm;
/**
 * 
 * VU MIF PS 1gr. 2012
 * @author Ernestas Prisakaru 
 * @author Lukas Ignatavicius
 * 
 */
public class CPU {
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
    private boolean CH1; // input
    private boolean CH2; // output
    private boolean CH3; // external storage
    //TODO hi-level processor
    //FIXME byte->short word->int
    public CPU() {
        IP = new byte[2];
        TIMER = new Timer(1);
        TI = 0;
        IOI = 0;
        SI = 0;
        PI = 0;
    }
}
