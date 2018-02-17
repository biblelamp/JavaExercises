/**
 * Java. Level 3. Lesson 3. Homework
 *
 * 1. Read file (about 50 bytes) to a byte array, output the array to the console
 * 2. Consistently merge 5 files into one (files about 100 bytes)
 * 3. 
 *
 * @author Sergey Iryupin
 * @version Feb 17, 2018
 */
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class HW3Lesson {

    public static void main(String[] args) {
        HW3Lesson hw = new HW3Lesson();
        hw.readFileInByteArray("hw3lesson.txt");
        hw.mergeFiles(new String[]{"hw3lesson5.txt",
            "hw3lesson.txt", "hw3lesson.txt", "hw3lesson.txt",
            "hw3lesson.txt", "hw3lesson.txt"});
    }

    void readFileInByteArray(String fileName) {
        byte[] buffer = new byte[100];
        int count = 0;
        try (FileInputStream input = new FileInputStream(fileName)) {
            count = input.read(buffer);
            System.out.println("Read " + count + " bytes.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < count; i++)
            System.out.printf("%x ", buffer[i]);
    }

    void mergeFiles(String[] files) {
        byte[] buffer = new byte[200];
        int count = 0;
        try (FileOutputStream output = new FileOutputStream(files[0])) {
            for (int i = 1; i < files.length; i++)
                try (FileInputStream input = new FileInputStream(files[i])) {
                    count = input.read(buffer);
                    output.write(buffer, 0, count);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}