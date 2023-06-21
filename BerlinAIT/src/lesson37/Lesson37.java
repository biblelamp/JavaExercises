package lesson37;

import java.util.Arrays;

public class Lesson37 {
    public static void main(String[] args) {
        SimpleBox box1 = new SimpleBox(5.5);
        SimpleBox box2 = new SimpleBox(3);

        if (box1.getValue() instanceof Integer && box2.getValue() instanceof Integer) {
            int value1 = (int) box1.getValue();
            int value2 = (int) box2.getValue();
            System.out.println(value1 + value2);
        } else {
            System.out.println("Error of cast");
        }

        GenericBox<Double> gb1 = new GenericBox<>(8.5);
        GenericBox<Integer> gb2 = new GenericBox<>(4);
        GenericBox<String> gb3 = new GenericBox<>("Hello");
        double result = gb1.getValue() + gb2.getValue(); // + gb3.getValue();
        System.out.println(result);
        //System.out.println("Hello: " + 12);

        // homework
        Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        System.out.println(Arrays.toString(array));

        exchange(array, 2, 6);
        System.out.println(Arrays.toString(array));

        String[] strs = {"Hello", "Java", "Generics"};
        System.out.println(Arrays.toString(strs));

        exchange(strs, 0, 2);
        System.out.println(Arrays.toString(strs));
    }

    static <T> void exchange(T[] array, int idx1, int idx2) {
        T temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

}
