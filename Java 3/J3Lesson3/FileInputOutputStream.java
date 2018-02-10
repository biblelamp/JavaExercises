import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

class FileInputOutputStream {

    public static void main(String[] args) {
        byte[] bw = {10, 20, 30};
        byte[] br = new byte[10];

        // writing to file
        try (FileOutputStream out = new FileOutputStream("12345.bin")) {
            out.write(bw);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // reading from file
        try (FileInputStream in = new FileInputStream("12345.bin")) {
            int count = in.read(br);
            System.out.println("Read " + count + " byte(s)");
            System.out.println(Arrays.toString(br));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}