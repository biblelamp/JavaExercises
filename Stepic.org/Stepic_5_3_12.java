import java.io.*;
import java.nio.charset.Charset;

class Stepic_5_3_12 {

    public static void main(String[] args) {
        byte[] data = {48, 49, 50, 51};
        Charset charset = Charset.forName("UTF-8"); //Charset.defaultCharset();
        InputStream is = new ByteArrayInputStream(data);
        try {
            System.out.println(readAsString(is, charset));
        } catch (Exception e) { e.printStackTrace(); }
    }

    /*
     * Method of reading data from the InputStream and converts to a string using the specified encoding 
     */

    public static String readAsString(InputStream inputStream, Charset charset) throws IOException {
        Reader reader = new InputStreamReader(inputStream, charset);
        int chr;
        StringBuilder sb = new StringBuilder();
        while ((chr = reader.read())!=-1){
            sb.append((char)chr);
        }
        return sb.toString();
    }
}