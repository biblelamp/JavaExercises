import java.util.Random;
import java.util.Scanner;

class JavaBasic4Lesson {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            System.out.print(random.nextInt(10) + " ");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n# ");
        int a = scanner.nextInt();
        System.out.println(a * 2);
    }
}