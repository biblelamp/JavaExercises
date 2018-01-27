class Stats<T extends Number> {
    private T[] nums;

    public Stats(T[] nums) {
        this.nums = nums;
    }

    public double average() {
        double sum = 0d;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i].doubleValue(); // error wihtout extends Number
        return sum / nums.length;
    }
}

public class StatsDemo {
    public static void main(String args[]) {
    Integer inums[] = {1, 2, 3, 4, 5};
    Stats<Integer> iob = new Stats<>(inums);
    System.out.println("Average value iob is " + iob.average());

    Double dnums[] = {1.1, 2.2, 3.3, 4.4, 5.5};
    Stats<Double> dob = new Stats<>(dnums);
    System.out.println("Average value dob is " + dob.average());

    // Это не скомпилируется, потому что String не является подклассом Number
    //String strs[] = {"1", "2", "3", "4", "5"};
    //Stats<String> strob = new Stats<>(strs);
    //double x = strob.average();
    //System.out.println("Среднее значение strob равно " + v);
    }
}