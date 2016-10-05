import java.io.*;
import java.util.*;

class Stepic_5_2_9 {

    /*
     * Program converts line feeds from Windows to Unix format.
     * The data in the format sent to Vindovs System.in,
     * the converted data is output to System.out.
     */

    public static void main(String[] args) throws IOException {
        ArrayList<Byte> ib = new ArrayList<Byte>();
        int is;
        InputStream ins = System.in;
        OutputStream outs = new BufferedOutputStream(System.out);
        while ((is = ins.read()) != -1) {
            ib.add((byte) is);
        }
        for (int i = 0; i < ib.size(); i++) {
            if (ib.get(i) == 13) {
                if (i != ib.size() - 1 && ib.get(i + 1) == 10) {
                    continue;
                } else outs.write(ib.get(i));
            } else {
                outs.write(ib.get(i));
            }
        }
        outs.flush();
        outs.close();
    }
}