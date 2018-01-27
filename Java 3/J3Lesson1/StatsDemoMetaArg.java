class Stats<T extends Number> {
    private T[] nums;

    public Stats(T[] nums) {
        this.nums = nums;
    }

    public double average() {
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i].doubleValue();
        return sum / nums.length;
    }

    public boolean sameAvg(Stats<?> obj) {
        return average() == obj.average();
    }
}

public class StatsDemoMetaArg {
    public static void main(String args[]) {
        Stats<Integer> iStats = new Stats<>(new Integer[]{1, 2, 3, 4, 5});
        double di = iStats.average();
        Stats<Double> dStats = new Stats<>(new Double[]{1.1, 2.2, 3.3, 4.4, 5.5});
        double dd = dStats.average();
        Stats<Float> fStats = new Stats<>(new Float[]{1.0f, 2.0f, 3.0f, 4.0f, 5.0f});
        double df = fStats.average();
        System.out.printf("di = %f; dd = %f; df = %f\n", di, dd, df);
        System.out.println("di == df -> " + iStats.sameAvg(fStats));
        System.out.println("di == dd -> " + iStats.sameAvg(dStats));
    }
}