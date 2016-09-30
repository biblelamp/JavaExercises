class Stepic_4_1_9 {

    public static void main(String[] args) {
        System.out.println(sqrt(4));
        System.out.println(sqrt(-4));
    }

    /*
     * Implement the sqrt() method with calculates the square root of a number,
     * if the parameter is negative should be thrown exception
     * java.lang.IllegalArgumentException with the message "Expected non-negative number, got ?"
     * instead of a question mark should be transferred number
     */

    static double sqrt(double x) {
        if (x < 0) {
            throw new IllegalArgumentException("Expected non-negative number, got " + x);
        } else {
            return Math.sqrt(x);
        }
    }
}