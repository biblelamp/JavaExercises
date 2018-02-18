/**
 * Java. Level 3. Lesson 3. Homework
 *
 * 1. Read file (about 50 bytes) to a byte array, output the array to the console
 * 2. Consistently merge 5 files into one (files about 100 bytes)
 * 3. Write a console application which reads text files by pages
 *    (file size >10 mb), enters the page number - the program output it to
 *    the console (the page will count 1800 characters)
 *
 * @author Sergey Iryupin
 * @version Feb 18, 2018
 */
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class HW3Lesson {

    public static void main(String[] args) {
        HW3Lesson hw = new HW3Lesson();
        /*
        hw.readFileInByteArray("hw3lesson.txt");
        hw.mergeFiles(new String[]{"hw3lesson5.txt",
            "hw3lesson.txt", "hw3lesson.txt", "hw3lesson.txt",
            "hw3lesson.txt", "hw3lesson.txt"});
        */
        hw.readByPages("E:\\Java\\The War of the Worlds.txt");
    }

    /**
     * 1. Read file (about 50 bytes) to a byte array, output to the console
     * @param fileName
     */
    void readFileInByteArray(String fileName) {
        byte[] buffer = new byte[100];
        int count = 0;
        try (FileInputStream input = new FileInputStream(fileName)) {
            count = input.read(buffer);
            System.out.println("Read " + count + " bytes.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < count; i++)
            System.out.printf("%x ", buffer[i]);
        System.out.println();
    }

    /**
     * 2. Merge 5 files into one (files about 100 bytes)
     * @param files[]
     */
    void mergeFiles(String[] files) {
        byte[] buffer = new byte[200];
        int count = 0;
        try (FileOutputStream output = new FileOutputStream(files[0])) {
            for (int i = 1; i < files.length; i++)
                try (FileInputStream input = new FileInputStream(files[i])) {
                    count = input.read(buffer);
                    output.write(buffer, 0, count);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 3. Read text files by pages
     * @param fileName
     */
    void readByPages(String fileName) {
        final int PAGE_SIZE = 1800;
        byte[] buffer = new byte[PAGE_SIZE];
        Scanner sc = new Scanner(System.in);
        File file = new File(fileName);
        int page = 0;
        do {
            System.out.print("Enter page [1.." +
                (file.length() / PAGE_SIZE + 1) + "], 0 - exit: ");
            page = sc.nextInt();
            if (page > 0)
                try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                    raf.seek((page - 1) * PAGE_SIZE);
                    int count = raf.read(buffer);
                    for (int i = 0; i < count; i++)
                        System.out.print((char)buffer[i]);
                    System.out.println();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        } while (page != 0);
    }
}