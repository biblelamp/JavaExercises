/**
 * Java. Level 3. Lesson 3. Homework
 * 1. Read the small file (about 1 kb) in a byte array
 * 2.
 *
 */
import java.io.*;

class HomeWork3 {

    public static void main(String[] args) {
        new HomeWork3().go();
    }

    void go() {
        getFileToByteArray("HomeWork3.java");
    }

    byte[] getFileToByteArray(String fileName) {
        File file = new File(fileName);
        byte[] fileBArray = new byte[(int)file.length()];
        try {
            FileInputStream fis = new FileInputStream((fileName));
            fis.read(fileBArray);
        } catch(IOException e) { e.printStackTrace(); }
        return fileBArray;
    }

}