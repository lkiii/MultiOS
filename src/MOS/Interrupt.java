package MOS;

/**
 *
 * @author ernestas
 */
public class Interrupt {
    public enum InterruptType {
        TIMEOUT, //
        MEMOUT,
        LOAD,
        GETDATA,
        HALT;
    }
    
    public int ID; // ar reik?
    public Process process = null;
    public InterruptType type;
   
    protected Interrupt(InterruptType type) {
        this.type = type;
    }
    
    protected Interrupt(InterruptType type, Process process) {
        this(type);
        this.process = process;
    }
    
}
