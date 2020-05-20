/**
 * Saving FOCAL programs in binary format
 */
class SaveFocalBin {

    /* head description

    EA 03       - constant (program download address)
    NN NN       - file length in bytes excluding the first four
    14 00 00 00 - yet leave as a constant
    43 3A 20 20 E6 EF EB E1 EC 2D E2 EB 30 30 31 30 8E 00 - constant
    */

    public static void main(String[] args) {
        
    }
}