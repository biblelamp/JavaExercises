import java.io.*;

class Stepic_5_2_8 {

    public static void main(String[] args) throws IOException {
        byte[] input = {0x13, 0x43, 0x02};
        InputStream is = new ByteArrayInputStream(input);
        System.out.println(checkSumOfStream(is));
    }

    /*
     * A method which reads an input stream and computes a checksum
     * The checksum is calculated by the following recurrent formula:
     * C[n+1] = rotateLeft(C[n]) xor b[n+1]
     */

    static int checkSumOfStream(InputStream is) throws IOException {
        int b;
        int checkSum = 0;
        try {
            while ((b = is.read()) != -1) {
                checkSum = Integer.rotateLeft(checkSum, 1) ^ b;
            }
        } catch (IOException e) {
            throw new IOException();
        }
        return checkSum;
    }
}