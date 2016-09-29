import java.util.function.DoubleUnaryOperator;

class Stepic_3_5_7 {

    public static void main(String[] args) {
        System.out.println("x -> 1, 0, 10: " + integrate(x -> 1, 0, 10));
        System.out.println("x -> x * x, -1, 1: " + integrate(x -> x * x, -1, 1));
    }

    /*
     * Implement a method of performing the numerical integration of a given function
     * in a given interval by the formula left rectangles
     */
    
    static double integrate(DoubleUnaryOperator f, double a, double b) {
        double dx = (b - a);
        double sum0;
        double sum1 = f.applyAsDouble(a) * (b - a);
        int it = 1;
        do {
            dx /= 2.;
            it *= 2;
            sum0 = sum1;
            sum1 = .0;
            for (double x = a, eps = dx / 2.; x + eps < b; x += dx) {
                sum1 += dx * f.applyAsDouble(x);
            }
        } while (Math.abs(sum0 - sum1) > 1e-7 && it < 100000000);
        return sum1;
    }
}