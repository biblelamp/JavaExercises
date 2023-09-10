package homework23;

/**
 * Java professional. Homework #23
 *
 * @author Sergey
 * @version 6.2.2023
 */
public class HomeWork23 {
    public static void main(String[] args) {
        PrintABC pab = new PrintABC();
        new Thread(() -> pab.print('A')).start();
        new Thread(() -> pab.print('B')).start();
        new Thread(() -> pab.print('C')).start();
    }
}
