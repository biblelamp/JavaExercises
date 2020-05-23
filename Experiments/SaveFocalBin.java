import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Saving FOCAL programs in binary format
 */
class SaveFocalBin {

    /* head description

    EA 03       - constant (program download address)
    NN NN       - file length in bytes -4 bytes
    14 00 00 00 - yet leave as a constant
    43 3A 20 20 E6 EF EB E1 EC 2D E2 EB 30 30 31 30 8E 00 - constant

    FE F6
    FB FE if 1 line = 6 bytes
    FB F6 if 2 line x 6 bytes (-8)
    FB EE if 3 line x 6 bytes (-8)
    FB E6 if 4 line x 6 bytes (-8)
    */

    static String path = "C:\\Program Files\\BKEmulator\\UserSaves\\";

    public static void main(String[] args) {
        byte[] head = {
            (byte)0xEA, 0x03,
            0x00, 0x00,
            0x14, 0x00, 0x00, 0x00, 
            0x43, (byte)0x3A, 0x20, 0x20, (byte)0xE6, (byte)0xEF, (byte)0xEB, (byte)0xE1, (byte)0xEC, (byte)0x2D, (byte)0xE2, (byte)0xEB, 0x30, 0x30, 0x31, 0x30,
            (byte)0x8E, 0x00,
            //(byte)0xF6, (byte)0xFB,
            //(byte)0x8E
            (byte)0xFE, (byte)0xFB,
            0x19, 0x01, 0x54, (byte)0x80, 0x31, 0x32, (byte)0x8E, 0x00
        };

        Map<Float, String> program = new HashMap<>();

        StretchArray dump = new StretchArray(head);
        dump.addAll(new byte[]{1, 2, 3});

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