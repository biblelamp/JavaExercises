/**
 * Java. Level 2. Lesson 2. Example of writing/reading file
 *
 * @author Sergey Iryupin
 * @version dated Dec 23, 2017
 */
import java.io.*;

class FileWriteRead {

    public static void main(String[] args) {

        // writing to file
        try (FileWriter file = new FileWriter("text.txt")) {
            file.write("Hello\nJava");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // reading from file
        try (FileReader file = new FileReader("text.txt")) {
            int b;
            while ((b = file.read()) != -1)
                System.out.print((char)b);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}