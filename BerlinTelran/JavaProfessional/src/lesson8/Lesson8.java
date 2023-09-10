package lesson8;

public class Lesson8 {
    public static void main(String[] args) {
        RubberList<Integer> rl = new RubberList<>();
        System.out.println(rl);
        rl.add(12);
        rl.add(4);
        System.out.println(rl);
        rl.remove(12);
        rl.add(8);
        System.out.println(rl);

    }
}
