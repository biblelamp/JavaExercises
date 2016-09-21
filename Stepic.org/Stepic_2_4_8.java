import java.math.*;

class Stepic_2_4_8 {

    public static void main(String[] args) {
        System.out.println(factorial(1));
        System.out.println(factorial(3));
    }

    /**
     * Calculates factorial of given value.
     *
     * @param value positive number
     * @return factorial of value
     */
    public static BigInteger factorial(int value) {
        if (value == 1)
            return BigInteger.valueOf(1);
        else
            return BigInteger.valueOf(value).multiply(factorial(value-1));
    }
}