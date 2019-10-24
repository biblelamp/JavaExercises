import java.util.Random;

class RandomInt {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            System.out.print(random.nextInt(10) + " ");
        }
    }
}