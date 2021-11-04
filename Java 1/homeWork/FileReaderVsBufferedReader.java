import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

class FileReaderVsBufferedReader {
    public static void main(String[] args) {
        String fileName = "C:\\temp\\imwhooser\\clo\\processed\\PNP_CLO.xml";
        testFileReader(fileName);
        testBufferedReader(fileName);
    }

    static void testFileReader(String fileName) {
        System.out.print("Testing FileReader... ");
        long t1 = System.currentTimeMillis();

        try (FileReader file = new FileReader(fileName)) {
            int x = -1;
            do
                x = file.read();
            while (x != -1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        long t2 = System.currentTimeMillis();
        System.out.println("It took " + (t2 - t1) + " mc");
    }

    static void testBufferedReader(String fileName) {
        System.out.print("Testing BufferedReader... ");
        long t1 = System.currentTimeMillis();

        try (BufferedReader file = new BufferedReader(
                new FileReader(fileName))) {
            int x = -1;
            do
                x = file.read();
            while (x != -1);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        long t2 = System.currentTimeMillis();
        System.out.println("It took " + (t2 - t1) + " mc");
    }
}