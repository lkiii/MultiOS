package rvm;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lukas
 */
public class ExternalMemory extends Memory {

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public ExternalMemory() {
        super();
        try {
            memory = (Word[])inputStream.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ExternalMemory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ExternalMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ExternalMemory(int size) {
        super(size);
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("src/momory.mem"));
            outputStream.writeObject(memory);
            outputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ExternalMemory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
