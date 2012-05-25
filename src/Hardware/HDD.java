package Hardware;

import java.io.*;
import java.util.ArrayList;
import Utils.Word;

/**
 * Kietasis diskas - vienas failas, is kurio imamas programos tekstas
 * @author ILikePancakes
 */
public class HDD {
    private BufferedReader reader;
    private BufferedWriter writer; 

    public HDD() {}

    // grazinama iš kietojo disko (failo) nuskaityta eilutė
    public String read() throws IOException {
        return reader.readLine();
    }

    // irasomas data
    public void write(ArrayList<String> data) {
        try {
            for (String s : data) {
                writer.append(s);
            } 
        } catch (IOException ex) { 
            throw new RuntimeException("IO readinant err");
        }
    }

    public boolean delete(String filename) {
        File f = new File("HDD/"+filename);
        if (!f.exists())
            throw new IllegalArgumentException();
        boolean success = f.delete();
        return success;
    }

    // visas sourcas nuskaitomas
    public ArrayList<Word> readAll() throws IOException {
        ArrayList<Word> sourceCode = new ArrayList();
        while (reader.ready()) {
            sourceCode.add(new Word(reader.readLine()));
        }
        return sourceCode;
    }
    
    /**
     * Programa turi rastis einamajame folderyje esanciame folderyje "HDD"
     */
    public void open(String filename, String mode) throws FileNotFoundException {
        try {
            switch (mode) {
                case "r":
                    reader = new BufferedReader(new FileReader("HDD/" + filename));
                    break;
                case "w":
                    writer = new BufferedWriter(new FileWriter("HDD/"+ filename));
                    break;
            }
        } catch (Exception ex) { 
            throw new AssertionError("HDDOPEN unknown mode");
        }
    }
    
    public void close() {
        try {
            if (reader != null) 
                reader.close();
            if (writer != null) 
                writer.close();
        } catch (IOException iOException) {
            throw new RuntimeException("xujus zhino");
        }
    }
    
}