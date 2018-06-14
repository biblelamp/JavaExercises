/**
 * Test task №1 from servermechanics.cz
 *  Write a program which displays the first 20 Fibonacci numbers,
 *  also compute their average
 *
 * @author Sergey Iryupin
 * @version dated Jun 13, 2013
 */
class Fibonacci {

    public static void main(String[] args) {
        new Fibonacci().computeAndShow(20);
    }

    void computeAndShow(int count) {
        if (count < 2)
            return;
        float total = 2;
        System.out.print("1 1 ");
        for (int i = 3, n0 = 1, n1 = 1, n2 = 2; i <= count;
                i++, n0 = n1, n1 = n2, n2 = n0 + n1) {
            total += n2;
            System.out.print(n2 + " ");
        }
        System.out.println("\nThe average is " + total / count);
    }
}