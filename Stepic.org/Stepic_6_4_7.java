import java.util.stream.*;
import java.math.BigInteger;

public class Stepic_6_4_7 {

    public static void main(String[] args) {
        System.out.println(factorial(5));
    }

    /*
     * Example counting a factorial using Stream API
     */

    static BigInteger factorial(int n) {
        return IntStream.rangeClosed(1, n)
        .mapToObj(i -> BigInteger.valueOf(i))
        .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}