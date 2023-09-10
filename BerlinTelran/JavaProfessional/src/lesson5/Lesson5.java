package lesson5;

public class Lesson5 {
    public static void main(String[] args) {
        RubberArray ra = new RubberArray();
        System.out.println(ra);
        System.out.println(ra.size());
        ra.addAll(12, 5, 3);
        System.out.println(ra);
        System.out.println(ra.contains(5));
        System.out.println(ra.contains(8));
        ra.remove(Integer.valueOf(5));
        System.out.println(ra);
    }
}
