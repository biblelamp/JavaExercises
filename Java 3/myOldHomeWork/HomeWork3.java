/**
 * Java. Level 3. Lesson 3. Homework
 * 1. Read the small file (about 1 kb) in a byte array
 * 2. Sew together 10 files one by one
 * 3. Read file (a small book of 20-50 kb) and count the number of unique words
 * 4. Read the book and count number of each letter of the alphabet
 * 5. Read the book and count number of vowels and consonants letters
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
        //for (Map.Entry<String, Integer> o : set) {
        //    System.out.println(o.getKey() + ": " + o.getValue());
        //}
        System.out.println("The book has " + hm.size() + " unique words.");

        // task 4
        int[] alphabet = new int[26];
        Arrays.fill(alphabet, 0);
        try (BufferedReader  reader = new BufferedReader (new InputStreamReader(new FileInputStream(BOOK_FILE_NAME)))) {
            int character;
            while ((character = reader.read()) != -1) {
                if (character >= (int)'A' && character <= (int)'Z') character += 32;
                if (character >= (int)'a' && character <= (int)'z') alphabet[character - 97]++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i< alphabet.length; i++)
            System.out.println((char)(i + 97) + ": " + alphabet[i]);

        // task 5
        int vowel = 0;
        int vowelCond = 0;
        int consonant = 0;
        try (BufferedReader  reader = new BufferedReader (new InputStreamReader(new FileInputStream(BOOK_FILE_NAME)))) {
            int character;
            while ((character = reader.read()) != -1) {
                if (character >= (int)'A' && character <= (int)'Z') character += 32;
                switch ((char)character) {
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'u': vowel++;
                        break;
                    case 'y':
                    case 'w': vowelCond++;
                        break;
                    default: consonant++;
                    
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("The number of vowels is %d, conditionally vowels is %d, consonants is %d", vowel, vowelCond, consonant));
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