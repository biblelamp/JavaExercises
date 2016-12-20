import java.io.*;
 
class ExampleReadFileLineByLine {

    public static void main(String[] args) {
        String line;
        try {
            BufferedReader reader = new BufferedReader(
                new FileReader(
                new File("ExampleReadFileLineByLine.java")));
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}