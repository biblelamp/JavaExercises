import java.io.*;

/**
 * Saving FOCAL programs in binary format
 */
class SaveFocalBin {

    /* head description

    EA 03       - constant (program download address)
    NN NN       - file length in bytes -4 bytes
    14 00 00 00 - yet leave as a constant
    43 3A 20 20 E6 EF EB E1 EC 2D E2 EB 30 30 31 30 8E 00 - constant
    */

    static String path = "C:\\Program Files\\BKEmulator\\UserSaves\\";

    public static void main(String[] args) {
        byte[] data = {
            (byte)0xEA, 0x03,
            0x00, 0x00,
            0x14, 0x00, 0x00, 0x00, 
            0x43, (byte)0x3A, 0x20, 0x20, (byte)0xE6, (byte)0xEF, (byte)0xEB, (byte)0xE1, (byte)0xEC, (byte)0x2D, (byte)0xE2, (byte)0xEB, 0x30, 0x30, 0x31, 0x30,
            (byte)0x8E, 0x00,
            (byte)0xFE, (byte)0xFB, 0x19, 0x01, 0x54, (byte)0x80, 0x31, (byte)0x8E
        };
        data[2] = (byte)(data.length - 4);
        String file = path + "H1.bin";
        writeFile(file, data);
    }

    static void writeFile(String file, byte[] data) {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}