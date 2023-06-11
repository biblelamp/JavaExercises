/**
 * Java Basic. Home work #2
 *
 * @author Sergey
 * @todo 7.9.2022
 * @date 10.9.2022
 */
class JavaBasic2Hw {
    public static void main(String[] args) {
        // task 1
        System.out.println(checkWeather(-5));
        System.out.println(checkWeather(5));
        System.out.println(checkWeather(20));
        System.out.println(checkWeather(30));

        // task 2
        System.out.println(canWalk(true, true)? "I can walk" : "I cannot walk");
        System.out.println(canWalk(false, true)? "I can walk" : "I cannot walk");
        System.out.println(canWalk(true, false)? "I can walk" : "I cannot walk");
        System.out.println(canWalk(false, false)? "I can walk" : "I cannot walk");

        // task 3
        int a = 5;
        int b = 3;
        System.out.printf("%d + %d = %d\n", a, b, add(a, b));
        System.out.printf("%d - %d = %d\n", a, b, sub(a, b));
        System.out.printf("%d * %d = %d\n", a, b, mul(a, b));
        System.out.printf("%d / %d = %f\n", a, b, div(a, b));

        // task 4
        System.out.printf("1: %s, ", dayOfWeek(1));
        System.out.printf("2: %s, ", dayOfWeek(2));
        System.out.printf("3: %s, ", dayOfWeek(3));
        System.out.printf("4: %s, ", dayOfWeek(4));
        System.out.printf("5: %s, ", dayOfWeek(5));
        System.out.printf("6: %s, ", dayOfWeek(6));
        System.out.printf("7: %s\n", dayOfWeek(7));
        System.out.printf("-1: %s\n", dayOfWeek(-1));

        // task 5
        canBuyFood(true, true);
        canBuyFood(false, true);
        canBuyFood(true, false);
        canBuyFood(false, false);

        // task 6
        double[] result;
        result = solveSquareEquation(1, -8, 12); // 5,3,7 no root; 1,-6,9 = 3; 1,-8,12 = 10,6
        if (result == null) {
            System.out.println("No root(s)");
        } else if (result.length == 1) {
            System.out.println("Result: " + result[0]);
        } else {
            System.out.println("Results: " + result[0] + ", " + result[1]);
        }
    }

    static String checkWeather(int temperature) {
        if (temperature < 0) {
            return "It’s frost";
        } else if (temperature <= 18) {
            return "It’s cold";
        } else if (temperature <= 28) {
            return "It’s warm";
        } else {
            return "It’s hot";
        }
    }

    static boolean canWalk(boolean isWeekend, boolean isRain) {
        return isWeekend && !isRain;
    }

    static int add(int a, int b) {
        return a + b;
    }

    static int sub(int a, int b) {
        return a - b;
    }

    static int mul(int a, int b) {
        return a * b;
    }

    static float div(float a, float b) {
        return a / b;
    }

    static String dayOfWeek(int day) {
        switch (day) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
            default:
                return "Incorrect day of the week number";
        }
    }

    static boolean canBuyFood(boolean isLidlOpen, boolean isTescoOpen) {
        if (isLidlOpen) {
            System.out.println("I can buy food in Lidl");
        } else if (isTescoOpen) {
            System.out.println("I can buy food in Tesco");
        } else {
            System.out.println("I can’t buy food");
        }
        return isLidlOpen || isTescoOpen;
    }

    static double[] solveSquareEquation(float a, float b, float c) {
        float d = b * b - 4 * a * c;
        if (d < 0) {
            return null;
        }
        if (d == 0) {
            double x = -b / (2 * a);
            return new double[]{x};
        }
        double x1 = -b + Math.sqrt(d) / (2 * a);
        double x2 = -b - Math.sqrt(d) / (2 * a);
        return new double[]{x1, x2};
    }
}