package MOS;

/**
 * ala supervizorines vector teiblas ir metodai jame
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
