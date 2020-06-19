import java.util.Arrays;

public class ForWithObjects {

    public static void main(String[] args) {
        new ForWithObjects();
    }

    public ForWithObjects() {
        One[] array = new One[5];
        for (int i = 0; i < array.length; i++) {
            One one = new One(i);
            array[i] = one;
        }
        for (One one : array) {
            one.setA(one.getA() * 2);
        }
        System.out.println(Arrays.toString(array));
    }

    class One {
        int a;

        One(int a) {
            this.a = a;
        }

        int getA() {
            return a;
        }

        void setA(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return String.format("A{%d}", a);
        }
    }

}