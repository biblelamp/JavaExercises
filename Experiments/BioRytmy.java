import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// B = (sin(2pi*t/P))*100 % where P = {23,28,33}
// more https://lyna.info/pravilnyj-algoritm-rascheta-bioritmov/

public class BioRytmy {

    public static void main(String[] args) {
        LocalDate first = LocalDate.of(1965, 12, 29);
        LocalDate second = LocalDate.now();//LocalDate.of(1965, 12, 30);

        long resultDays = ChronoUnit.DAYS.between(first, second);

        double f = Math.sin(2*Math.PI*resultDays/23)*100;
        double e = Math.sin(2*Math.PI*resultDays/28)*100;
        double i = Math.sin(2*Math.PI*resultDays/33)*100;

        System.out.println(first);
        System.out.println(second);
        System.out.println(resultDays);

        System.out.printf("Physically %.2f%%\n", f);
        System.out.printf("Emotionally %.2f%%\n", e);
        System.out.printf("Intellectually %.2f%%\n", i);

    }
}
