package lesson27.homework;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #27
 *
 * @author Sergey Iryupin
 * @version 18-Mar-24
 */
public class HomeWork27 {
    public static void main(String[] args) {
        // task #1
        BankAccount account1 = new BankAccount();
        account1.depositMoney(1000);
        BankAccount account2 = new BankAccount();
        account2.depositMoney(1500);
        account1.transferMoney(250, 0, account2);
        System.out.println("#1:" + account1.checkBalance() + ", #2:" + account2.checkBalance());

        account1.transferMoney(0, 150, account2);
        System.out.println("#1:" + account1.checkBalance() + ", #2:" + account2.checkBalance());

        // task #2
        Triathlete triathlete = new Triathlete();
        triathlete.run();
        triathlete.swim();
    }
}