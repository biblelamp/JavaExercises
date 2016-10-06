import java.util.Scanner;

class Stepic_5_3_13 {

    /*
     * Program reads text from System.in and outputs to System.out sum of all
     * real numbers in the text up to the sixth decimal place
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double d = .0;
        while(sc.hasNext()) {
            try {
                d += Double.parseDouble(sc.next());
            } catch (NumberFormatException e) {};
        }
        System.out.format("%.6f", d);
    }
}