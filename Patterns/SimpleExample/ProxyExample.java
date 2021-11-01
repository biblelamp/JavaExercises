interface IMath {
    double add(double x, double y);
    double sub(double x, double y);
    double mul(double x, double y);
    double div(double x, double y);
}

class MathHard implements IMath {

    public double add(double x, double y) {
        return x + y;
    }

    public double sub(double x, double y) {
        return x - y;
    }

    public double mul(double x, double y) {
        return x * y;
    }

    public double div(double x, double y) {
        return x / y;
    }
}

class MathProxy implements IMath {
    private MathHard mathHard;

    private void lazyInitMath() {
        if (mathHard == null) {
            mathHard = new MathHard();
        }
    }

    public double add(double x, double y) {
        lazyInitMath();
        return mathHard.add(x, y);
    }

    public double sub(double x, double y) {
        lazyInitMath();
        return mathHard.sub(x, y);
    }

    public double mul(double x, double y) {
        lazyInitMath();
        return mathHard.mul(x, y);
    }

    public double div(double x, double y) {
        lazyInitMath();
        return mathHard.div(x, y);
    }
}

public class ProxyExample {
    public static void main(String[] args) {
        IMath p = new MathProxy();

        System.out.println("4 + 2 = " + p.add(4, 2));
        System.out.println("4 - 2 = " + p.sub(4, 2));
        System.out.println("4 * 2 = " + p.mul(4, 2));
        System.out.println("4 / 2 = " + p.div(4, 2));
    }
}