package lesson6;

public class Mortgages {
    public static void main(String[] args) {
        float total = 200000;
        float payment = 1000;
        float percent = 4; // %
        int monthCount = 0;
        do {
            monthCount++;
            if (monthCount % 12 == 0) {
                total = total + total/100*percent;
            }
            total = total - payment;
        } while (total > 0);
        System.out.println(monthCount);
    }
}
