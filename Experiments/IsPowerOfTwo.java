/*
 * How to know if a number is a power of two
 */

 class IsPowerOfTwo {

    public static void main(String[] args) {
        long[] a = {4, 1024, 2048, 0xd9af54e703bb08deL};
        for (long x : a) {
            long y = (x & (x - 1)); // y must equal to 0 if x is a power of two
                                    // OR (x & (~x + 1)) - y must equal to x
            System.out.println(x + ":" + y);
        }
    }
}