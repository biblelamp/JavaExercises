/**
 * StrToDouble: converts a string like "123.456" to a double
 *
 * @author Sergey Iryupin
 * @version dated Aug 07, 2017
 */
class StringToDouble {

    public static void main(String[] args) {
        System.out.println(StrToDouble("12345.25"));
        System.out.println(StrToDouble("0.1234"));
        System.out.println(StrToDouble(".567"));

        System.out.println("123456789".substring(8 - 7, 8));
    }

    static double StrToDouble(String str) {
        double result = 0;
        double fraction = 0;
        double divider = 1;
        boolean isFraction = false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '.') {
                int digit = ((int) str.charAt(i)) - 48;
                if (!isFraction)
                    result = result * 10 + digit;
                else {
                    divider *= 10;
                    fraction += digit / divider;
                }
            } else
                isFraction = true;
        }
        return result + fraction;
    }
}