package lesson23;

public class PrintABDemo {
    public static void main(String[] args) {
        PrintAB pab = new PrintAB();
        new Thread(() -> pab.printA()).start();
        new Thread(() -> pab.printB()).start();
    }
}
