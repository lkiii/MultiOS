package rvm;
/**
 * 
 * VU MIF PS 1gr. 2012
 * @author Ernestas Prisakaru 
 * @author Lukas Ignatavicius
 * 
 */

// import static -> constant without prefix
public final class Constants {
    public static final int WORD_SIZE = 0x4;
    public static final int BLOCK_SIZE = 0xf;
    public static final int PT_SIZE = 0xf;
    
    private Constants() {
        throw new AssertionError("<Constants> is uninstansiable");
    }
    
}
