import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * Saving FOCAL programs in binary format
 */
class SaveFocalBin {

    /* head description

    EA 03       - constant (program download address)
    NN NN       - file length in bytes -4 bytes
    14 00 00 00 - yet leave as a constant
    43 3A 20 20 E6 EF EB E1 EC 2D E2 EB 30 30 31 30 8E 00 - constant

    FB F6
    FB FE if 1 line = 6 bytes
    FB F6 if 2 line x 6 bytes (-8)
    FB EE if 3 line x 6 bytes (-8)
    FB E6 if 4 line x 6 bytes (-8)
    */

    private final static String path = "C:\\Program Files\\BKEmulator\\UserSaves\\";

    private final static String LINE_FORMAT = "%05.2f %s\n";

    private static int startAddr = 0x0FBFE;

    public static void main(String[] args) {
        byte[] head = {
            (byte)0xEA, 0x03,
            0x00, 0x00,
            0x14, 0x00, 0x00, 0x00, 
            0x43, (byte)0x3A, 0x20, 0x20, (byte)0xE6, (byte)0xEF, (byte)0xEB, (byte)0xE1, (byte)0xEC, (byte)0x2D, (byte)0xE2, (byte)0xEB, 0x30, 0x30, 0x31, 0x30,
            (byte)0x8E, 0x00
        };
        StretchArray dump = new StretchArray(head);

        Map<Float, String> prg = new TreeMap<>();
        prg.put(1.1f, "S A=3");
        prg.put(1.2f, "T A*A,!");
        prg.put(1.3f, "Q");

        int counter = 0;
        for (Float key : prg.keySet()) {
            System.out.print(String.format(Locale.ROOT, LINE_FORMAT, key, prg.get(key)));
            counter++;
            byte[] line = toByte(key, prg.get(key), counter == prg.size());
            dump.addAll(line);
            startAddr -= line.length;
        }


        dump.buffer[2] = (byte)(dump.buffer.length - 4);
        String file = path + "H1.bin";
        writeFile(file, dump.buffer);
        System.out.println(file);
    }

    static void writeFile(String file, byte[] data) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static byte[] toByte(Float key, String line, boolean lastLine) {
        byte[] result = new byte[line.length() + 5 + (line.length()%2 == 0? 1 : 0)];
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 0x20) { // space
                result[i + 4] = (byte)0x80;
            } else if (line.charAt(i) == 0x3D) { // =
                result[i + 4] = (byte)0x8F;
            } else if (line.charAt(i) == 0x2A) { // *
                result[i + 4] = (byte)0x84;
            } else if (line.charAt(i) == 0x2C) { // ,
                result[i + 4] = (byte)0x8C;
            } else {
                result[i + 4] = (byte)line.charAt(i);
            }
        }
        byte length = (byte)(line.length() + 4 + (line.length()%2 == 0? 0 : 1));
        if (lastLine) {
            String hex = Integer.toHexString(startAddr);
            result[0] = (byte) Integer.parseInt(hex.substring(2, 4), 16);
            result[1] = (byte) Integer.parseInt(hex.substring(0, 2), 16);
        } else {
            result[0] = (byte)(length - 2);
        }
        Float second = (key - key.intValue())*100 * 2.55f;
        result[2] = (byte)second.intValue();
        result[3] = (byte)key.intValue();
        result[line.length() + 2 + (line.length()%2 == 0? 1 : 2)] = (byte)0x8E;
        return result;
    }

    static class StretchArray {
        byte[] buffer;

        StretchArray(byte[] init) {
            buffer = init;
        }
 
        void add(byte item) {
            byte[] temp = new byte[buffer.length + 1];
            System.arraycopy(buffer, 0, temp, 0, buffer.length);
            temp[buffer.length] = item;
            buffer = temp;
        }

        void addAll(byte[] items) {
            byte[] temp = new byte[buffer.length + items.length];
            System.arraycopy(buffer, 0, temp, 0, buffer.length);
            System.arraycopy(items, 0, temp, buffer.length, items.length);
            buffer = temp;
        }
    }

}