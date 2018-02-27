/**
 * Java. Level 3. Lesson 4. Homework
 *
 * 1. Create 3 threads, each of which prints the letter (A, B and C) 5 times,
 *    the order should be exactly ABCABCABS. Use wait/notify/notifyAll
 * 2. Write a method in which 3 threads write data to a file line by line
 *    (for 10 records, with a period of 20 ms)
 * 3. Write a MFD class, where you can print and scan documents at the same
 *    time, but you can not print two documents or scan at the same time
 *    (when printing, write to the console "printed 1,2,3,... pages",
 *    when scanning the same, only "scanned ...", output to the console
 *    with a period of 50 ms)
 *
 * @author Sergey Iryupin
 * @version Feb 25, 2018
 * @link https://github.com/biblelamp
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

public class HW4Lesson {
    final Object monitor = new Object();
    volatile char currentLetter = 'A';

    public static void main(String[] args) throws InterruptedException {
        HW4Lesson hw = new HW4Lesson();

        // 1 stage
        new Thread(() -> hw.printLetter('A', 'B', 5)).start();
        new Thread(() -> hw.printLetter('B', 'C', 5)).start();
        new Thread(() -> hw.printLetter('C', 'A', 5)).start();
        System.out.println();

        // 2 stage
        new Thread(() -> hw.printFile(new File("hw4lesson.txt"), 10)).start();
        new Thread(() -> hw.printFile(new File("hw4lesson.txt"), 10)).start();
        new Thread(() -> hw.printFile(new File("hw4lesson.txt"), 10)).start();

        // 3 stage
        MFD mfd = new MFD();
        new Thread(()->mfd.print("doc1", 5)).start();
        new Thread(()->mfd.print("doc2", 5)).start();

        Thread.sleep(1000);
        System.out.println();

        new Thread(()->mfd.scan("doc3", 5)).start();
        new Thread(()->mfd.scan("doc4", 5)).start();

        Thread.sleep(1000);
        System.out.println();

        new Thread(()->mfd.scan("doc5", 5)).start();
        new Thread(()->mfd.print("doc6", 5)).start();
    }

    void printLetter(char mainLetter, char nextLetter, int times) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < times; i++) {
                    while (currentLetter != mainLetter)
                        monitor.wait();
                    System.out.print(mainLetter);
                    currentLetter = nextLetter;
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void printFile(File file, int times) {
        synchronized (file) {
            for (int i = 0; i < times; i++)
                try (FileWriter pw = new FileWriter(file, true)) {
                    pw.write(
                        Thread.currentThread().getName() + ": " + i + "\n");
                    Thread.sleep(20);
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
        }
    }
}

class MFD {
    private final Object printer = new Object();
    private final Object scanner = new Object();

    private void doIt(Object device, String docName, int pages) {
        synchronized (device) {
            for (int i = 0; i < pages; i++)
                try {
                    System.out.printf("%s page %d:%d of '%s'\n",
                        (device.equals(printer)? "Printed" : "Scanned"),
                        i + 1, pages, docName);
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
        }
    }

    public void print(String docName, int pages) {
        doIt(printer, docName, pages);
    }

    public void scan(String docName, int pages) {
        doIt(scanner, docName, pages);
    }
}