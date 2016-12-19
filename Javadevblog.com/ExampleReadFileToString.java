import java.io.*; // BufferedReader
import java.nio.file.*; // class Files
import java.nio.charset.*;
import java.util.*; // Scanner
import org.apache.commons.io.*; // https://commons.apache.org/proper/commons-io/download_io.cgi
/*
 * I have to put commons-io-2.5 to C:\Program Files\Java\jdk1.8.0_102\jre\lib\ext for javac
 */
 
class ExampleReadFileToString {
 
    public static void main(String[] args) throws IOException {
        String fileName = "ExampleReadFileToString.java";
        String contents;

        // read file using Scanner
        //contents = readUsingScanner(fileName);

        // read file using Apache Commons IO
        contents = readUsingApacheCommonsIO(fileName);

        // read file using class Files
        //contents = readUsingFiles(fileName);

        // read file using BufferedReader
        //contents = readUsingBufferedReader(fileName);

        // output file
        System.out.println(contents);
        System.out.println(contents.length());
    }

    // read file to String using BufferedReader
    private static String readUsingBufferedReader(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

    // read file using class Files
    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    // read file using Apache Commons IO
    private static String readUsingApacheCommonsIO(String fileName) throws IOException {
        return FileUtils.readFileToString(new File(fileName), StandardCharsets.UTF_8);
    }

    // read file using Scanner
    private static String readUsingScanner(String fileName) throws IOException {
        Scanner scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        String data = scanner.useDelimiter("\\A").next();
        scanner.close();
        return data;
    }
}