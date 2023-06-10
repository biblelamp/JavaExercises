/**
 * Java Basic. Home work #1
 *
 * @author Sergey
 * @todo 5.9.2022
 * @date 7.9.2022
 */
class JavaBasic1Hw {
    public static void main(String[] args) {
        // task 1
        int a = 12;
        int b = 8;
        int p = (a + b) * 2;
        int s = a * b;
        System.out.println("Perimeter = " + p);
        System.out.println("Area = " + s);

        // task 2
        a = 5;
        b = 3;
        System.out.printf("%d + %d = %d\n", a, b, a + b);
        System.out.printf("%d - %d = %d\n", a, b, a - b);
        System.out.printf("%d * %d = %d\n", a, b, a * b);
        System.out.printf("%d / %d = %f\n", a, b, (float)a / b);

        // task 3
        int[] array = {5, 2, 8, 4, 7};
        System.out.printf("[%d, %d, %d, %d, %d]\n", array[0], array[1], array[2], array[3], array[4]);

        // task 4
        int pizzaDiameter = 28;
        double pizzaRadius = pizzaDiameter / 2d;
        double pizzaArea = Math.PI * pizzaRadius * pizzaRadius;
        double pizzaCalories = pizzaArea * 40;
        System.out.printf("Pizza %d cm in diameter has %.2f calories", pizzaDiameter, pizzaCalories);
    }
}