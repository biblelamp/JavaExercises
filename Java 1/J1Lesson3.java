import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class J1Lesson3 {
    public static void main(String[] args) {
        int[] arr = {6, 4, 9, 2, 3, 7, 0};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(arr[0] + " - " + arr[arr.length - 1]);

        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            System.out.print(random.nextInt(25) + " ");
        }

        System.out.println("\nEnter a operation b:");
        Scanner read = new Scanner(System.in);
        int a = read.nextInt();
        String s = read.next();
        int b = read.nextInt();

        switch (s) {
            case "+": System.out.println("= " + (a + b));
                break;
            case "-": System.out.println("= " + (a - b));
                break;
        }
    }
}