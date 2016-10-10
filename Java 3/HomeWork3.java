/**
 * Java. Level 3. Lesson 3. Homework
 * 1. Read the small file (about 1 kb) in a byte array
 * 2. Sew together 10 files one by one
 *
 */
import java.io.*;

class HomeWork3 {

    final String IN_FILE_NAME = "HomeWork3.java";
    final String OUT_FILE_NAME = "file_x10.txt";

    public static void main(String[] args) {
        new HomeWork3().go();
    }

    void go() {
        // task 1
        getFileToByteArray(IN_FILE_NAME);
        
        // task 2
        try (FileOutputStream fos = new FileOutputStream(OUT_FILE_NAME, true)) {
            for (int i = 0; i < 10; i++) {
                byte[] byteArray = getFileToByteArray(IN_FILE_NAME);
                fos.write(byteArray);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    byte[] getFileToByteArray(String fileName) {
        File file = new File(fileName);
        byte[] fileBArray = new byte[(int)file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileBArray);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return fileBArray;
    }

}