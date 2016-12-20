import java.io.*;
import java.nio.file.*;

public class ExampleWriteFile {

    public static void main(String[] args) {
        String data = "This is a test string to write to the file";
        int noOfLines = 10000;

        // write file using FileWriter
        writeUsingFileWriter(data);
 
        // write file using BufferdWriter         
        writeUsingBufferedWriter(data, noOfLines);
         
        // write file using Files
        writeUsingFiles(data);
         
        // write file using OutputStream
        writeUsingOutputStream(data);
    }

     // write file using OutputStream
     static void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File("FileOutputStream.txt"));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
     
    // write file using Files
    static void writeUsingFiles(String data) {
        try {
            Files.write(Paths.get("Files.txt"), data.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // write file using BufferedWriter
    static void writeUsingBufferedWriter(String data, int noOfLines) {
        File file = new File("BufferedWriter.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine = data + System.getProperty("line.separator");
        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            for(int i = noOfLines; i>0; i--) {
                br.write(dataWithNewLine);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // write file using FileWriter
    static void writeUsingFileWriter(String data) {
        File file = new File("FileWriter.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}