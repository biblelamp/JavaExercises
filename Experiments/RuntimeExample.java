import java.io.*;

public class RuntimeExample {

    public static void main(String[] args) {

        try {
            Process runtime = Runtime.getRuntime().exec("cmd /c start dir *.java");
        } catch (Exception e) { e.printStackTrace(); }
    }
}