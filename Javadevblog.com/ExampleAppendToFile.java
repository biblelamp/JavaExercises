import java.io.*;
 
class ExampleAppendToFile {

    public static void main(String[] args) {
        String filePath = "update.txt";
        String appendText = "Added to the text file";

        // append to file using FileWriter
        appendUsingFileWriter(filePath, appendText);

        // append to file using BufferedWriter         
        appendUsingBufferedWriter(filePath, appendText, 10);
         
        // append to file using OutputStream
        appendUsingOutputStream(filePath, appendText);
    }

    // append to file using FileWriter
    static void appendUsingOutputStream(String fileName, String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(fileName), true);
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

    // append to file using BufferedWriter
    static void appendUsingBufferedWriter(String filePath, String text, int noOfLines) {
        File file = new File(filePath);
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file,true);
            br = new BufferedWriter(fr);
            for(int i = 0; i < noOfLines; i++) {
                br.newLine();
                br.write(text);
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

    // append to file using FileWriter
    private static void appendUsingFileWriter(String filePath, String text) {
        File file = new File(filePath);
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write(text);
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