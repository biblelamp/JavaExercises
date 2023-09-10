package lesson24;

/**
 * 
 *
 */

public class PrinterDemo {
    public static void main(String[] args) {
        Document d1 = new Document("doc1", 10);
        Document d2 = new Document("doc2", 8);
        Document d3 = new Document("doc3", 15);
        Printer printer = new Printer();
        new Thread(() -> printer.print(d1)).start();
        new Thread(() -> printer.print(d2)).start();
        new Thread(() -> printer.print(d3)).start();
    }
}
