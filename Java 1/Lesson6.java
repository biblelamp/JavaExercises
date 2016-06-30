/**
 * Java. Level 1. Lesson 6. Homework
 *	+	Create 2 text files with about 100-150 characters;
 *	+	Write a program to "gum up" these files, that is first is the text of the first file, then the text of the second.
 *	+	* Write a program that makes the list of file names in which the searching word is present.
 *		To check the operation of the program need to create ~ 10 text files.
 * 
 * @author Sergey Iryupin
 * @version 28 June 2016
 */
import java.util.Scanner;
import java.io.*;

public class Lesson6 {

    public static void main(String[] args) {
        /*
         * join the contents of two files
         */
        int i;
        final String DIR_NAME = "texts";
        String[] fn = { "text_1.txt", "text_2.txt" };
        String newfile = "text_1_2.txt";
        FileInputStream fin = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(DIR_NAME + "/" + newfile);
            for (String name : fn) {
                fin = new FileInputStream(DIR_NAME + "/" + name);
                do {
                    i = fin.read();
                    if (i != - 1) fout.write(i);
                } while (i != -1);
                fout.write(0x0A);
                if (fin != null) fin.close();
            }
            if (fout != null) fout.close();
        } catch (IOException e) {
                System.out.println("Error: " + e.toString());
        }
        System.out.println("File: " + newfile + " created successfully.");
        /*
         * looking in the files a string of characters
         */
        String line;
        boolean found;
        
        Scanner read = new Scanner(System.in);
        System.out.print("Enter searching word: ");
        String searchword = read.next();
        
        File f1 = new File(DIR_NAME);
        String s[] = f1.list();
        for (i=0; i < s.length; i++) {
            File f = new File(DIR_NAME + "/" + s[i]);
            found = false;
            if (!f.isDirectory()) {
                try {
                    FileReader fr = new FileReader(DIR_NAME + "/" + s[i]);
                    BufferedReader br = new BufferedReader(fr);
                    while ((line = br.readLine()) != null) {
                        if (line.contains(searchword)) {
                            found = true;
                            break;
                        }
                    }
                    fr.close();
                    if (found) {
                        System.out.println(s[i]);
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e.toString());
                }
            }
        }
    }
}