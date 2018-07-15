import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Collections;

class SequenceInputStreamEx {
    public static void main(String[] args) {
        List<InputStream> al = new ArrayList<>();
        SequenceInputStream seq = null;
        try (FileInputStream in1 = new FileInputStream("1.txt");
                FileInputStream in2 = new FileInputStream("2.txt");
                FileOutputStream out = new FileOutputStream("3.txt")) {
            al.add(in1);
            al.add(in2);
            Enumeration<InputStream> list = Collections.enumeration(al);
            seq = new SequenceInputStream(list); //instead (in1, in2);
            int rb = seq.read();
            while (rb != -1) {
                out.write(rb);
                rb = seq.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try { seq.close(); } catch ( IOException e ) { };
        }
    }
}