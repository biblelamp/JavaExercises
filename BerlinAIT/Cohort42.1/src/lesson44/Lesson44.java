package lesson44;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AIT-TR, cohort 42.1, Java Basic, Lesson #44
 *
 * @version 29-Apr-24
 */
public class Lesson44 {
    public static void main(String[] args) {
        File fileBin = new File("file.bin"); //"textfile.txt");
        System.out.println(fileBin.exists());

        // byte array input stream
        byte[] buf = {1, 2, 3, 4, 5, 6}; //{65, 66, 67};
        ByteArrayInputStream is = new ByteArrayInputStream("klobása".getBytes());
        int x;
        while ((x = is.read()) != -1) {
            System.out.print(x + " ");
        }
        System.out.println();

        // write: file output stream
        try (FileOutputStream fos = new FileOutputStream(fileBin)) {
            fos.write(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read: file input stream
        int bufferSize = Long.valueOf(fileBin.length()).intValue();
        byte[] inputBuffer = new byte[bufferSize];

        try (FileInputStream fis = new FileInputStream(fileBin)) {
            fis.read(inputBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(inputBuffer));

        String[] text = {"line 1", "line 2", "line 3"};
        File textFile = new File("filetxt.txt");

        // write: file reader
        try (FileWriter writer = new FileWriter(textFile)) {
            for (String str : text) {
                writer.write(str + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int charBufSize = Long.valueOf(textFile.length()).intValue();
        char[] charBuff = new char[charBufSize];

        // read: file reader
        try (FileReader reader = new FileReader(textFile)) {
            reader.read(charBuff);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(charBuff));

        // read by line using package nio
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("components.txt"));
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // process components
        List<ExtСomponent> components = new ArrayList<>();
        for (String line : lines) {
            String[] fields = line.split(",");
            System.out.println(Arrays.toString(fields));
            ExtСomponent extСomponent = new ExtСomponent(fields[1], Integer.valueOf(fields[2]));
            components.add(extСomponent);
        }
        System.out.println(components);
    }
}
