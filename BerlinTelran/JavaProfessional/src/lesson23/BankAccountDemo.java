package lesson23;

public class BankAccountDemo {
    public static void main(String[] args) throws InterruptedException {
        BankAccount ba = new BankAccount(250);
        Thread p1 = new Thread(() -> ba.pay(80, "Mike"));
        Thread p2 = new Thread(() -> ba.pay(100, "Mark"));
        Thread p3 = new Thread(() -> ba.pay(150, "Bill"));
        Thread p4 = new Thread(() -> ba.pay(75, "John"));
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p1.join();
        p2.join();
        p3.join();
        p4.join();
        System.out.println(ba);
    }
}
