/**
 * Java. Level 3. Lesson 3. Homework
 * 1. Read the small file (about 1 kb) in a byte array
 * 2. Sew together 10 files one by one
 * 3. Read file (a small book of 20-50 kb) and count the number of unique words
 *
 */
import java.io.*;
import java.util.*;

class HomeWork3 {

    final String IN_FILE_NAME = "HomeWork3.java";
    final String OUT_FILE_NAME = "file_x10.txt";
    final String BOOK_FILE_NAME = "Dead_Mans_Island-John_Escott.txt";

    public static void main(String[] args) {
        new HomeWork3().go();
    }

    void go() {
        // task 1
        getFileToByteArray(IN_FILE_NAME);

        // task 2
        try (FileOutputStream fos = new FileOutputStream(OUT_FILE_NAME, true)) {
            for (int i = 0; i < 10; i++) {
                byte[] byteArray = getFileToByteArray(IN_FILE_NAME);
                fos.write(byteArray);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        // task 3
        TreeMap<String, Integer> hm = new TreeMap<String, Integer>();
        Set<Map.Entry<String, Integer>> set = hm.entrySet();
        try (BufferedReader  reader = new BufferedReader (new InputStreamReader(new FileInputStream(BOOK_FILE_NAME)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                String[] words = line.toLowerCase().split("[\t -\"\'{,|.|!|?}?]+");
                for (String word : words) {
                    int value = 0;
                    try {
                        value = hm.get(word);
                    } catch(NullPointerException e) { }
                    hm.put(word, value + 1);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, Integer> o : set) {
            System.out.println(o.getKey() + ": " + o.getValue());
        }
        System.out.println("The book has " + hm.size() + " unique words.");

    }

    byte[] getFileToByteArray(String fileName) {
        File file = new File(fileName);
        byte[] fileBArray = new byte[(int)file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileBArray);
        } catch(IOException e) {
            e.printStackTrace();
        }
        return fileBArray;
    }

}