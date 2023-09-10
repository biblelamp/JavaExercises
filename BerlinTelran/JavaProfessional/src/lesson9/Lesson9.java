package lesson9;

import java.util.Iterator;

public class Lesson9 {
    public static void main(String[] args) {
        RubberList<Integer> rl = new RubberList<>();
        rl.addAll(3, 5, 12, 8, 15);
        System.out.println(rl);

        for (Integer item : rl) {
            System.out.println(item);
        }

        Iterator<Integer> iterator = rl.iterator();
        while (iterator.hasNext()) {
            Integer value = iterator.next();
            if (value > 10) {
                iterator.remove();
            }
        }
        System.out.println(rl);
        RubberList<Integer> stack = new RubberList<>();
        for (int i = 0; i < 3; i++) {
            stack.push(i * 5);
            System.out.println(stack);
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(stack.pop());
        }
    }
}
