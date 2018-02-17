/**
 * Java. Level 3. Lesson 3. Homework
 *
 * 1. Read file (about 50b) to a byte array, output the array to the console
 * 2.
 * 3.
 *
 * @author Sergey Iryupin
 * @version Feb 17, 2018
 */
import java.io.IOException;
import java.io.FileInputStream;

public class HW3Lesson {

    public static void main(String[] args) {
        HW3Lesson hw = new HW3Lesson();
        hw.readFileInByteArray("hw3lesson.txt");
    }

    void readFileInByteArray(String fileName) {
        byte[] buffer = new byte[100];
        int count = 0;
        try (FileInputStream input = new FileInputStream(fileName)) {
            count = input.read(buffer);
            System.out.println("Read " + count + " bytes.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < count; i++)
            System.out.printf("%x ", buffer[i]);
    }
}