package rvm;
import static rvm.Constants.*;
/**
 *
 * @author lukas ir ernis
 */
public class VirtualMemorySWAP extends VirtualMemory{

    protected Memory externalMemory;
    
    public VirtualMemorySWAP(Word PTR, Memory rMemory, Memory eMemory) {
        super(PTR, rMemory);
        externalMemory = eMemory;
    }
    
    @Override
    public Word readWord(int track, int word) {
        if (pageTableAddress.toInt() > PT_SIZE){
            return new Word(0);
        }else{
            return super.readWord(track, word);
        }
    }
    @Override
    public void writeWord(int track, int word, Word data) {
        if (pageTableAddress.toInt() > PT_SIZE){
            
        }else{
            super.writeWord(track, word, data);
        }
    }
}
