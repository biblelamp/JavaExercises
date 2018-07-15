import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

class ArrayInputOutputStream {
    public static void main(String[] args) {
        // input stream
        byte[] input = {100, 25, 50};
        ByteArrayInputStream in = new ByteArrayInputStream(input);
        int x;
        while((x = in.read()) != -1)
            System.out.print(x + " ");
        System.out.println();

        // output stream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(10);
        out.write(20);
        out.write(30);
        byte[] output = out.toByteArray();
        System.out.println(Arrays.toString(output));
    }
}