package rvm;

public class CPU {

    private Word PTR;
    private Word R1;
    private Word R2;
    private byte[] IP;
    private boolean C;
    private byte MODE;
    private Timer TIMER;
    private byte TI;
    private byte IOI;
    private byte SI;
    private byte PI;
    private boolean CH1;
    private boolean CH2;
    private boolean CH3;

    public CPU() {
        IP = new byte[2];
        TIMER = new Timer();
        TI = 0;
        IOI = 0;
        SI = 0;
        PI = 0;
    }
}
