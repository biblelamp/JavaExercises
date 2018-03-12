/**
 * Java. Level 1. Lesson 3. Example of writing/reading file
 *
 * @author Sergey Iryupin
 * @version dated Mar 12, 2018
 */
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

class FileWriteRead {

    public static void main(String[] args) {

        // writing to file
        try (FileWriter file = new FileWriter("text.txt")) {
            file.write("Hello\nJava");
            //file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // reading from file
        try (FileReader file = new FileReader("text.txt")) {
            int b;
            while ((b = file.read()) != -1)
                System.out.print((char)b);
            //file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}