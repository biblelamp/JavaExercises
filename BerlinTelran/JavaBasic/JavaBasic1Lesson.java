class JavaBasic1Lesson {
    public static void main(String[] args) {
        // integer types: int, byte, short, long
        int a = 10;
        System.out.println("our variable is " + a);
        // non integer types: float, double
        float b = 5 / 2f;
        System.out.println(b);
        // character's type: char
        char c = 'q';
        System.out.println(c);
        // boolean type: boolean (true/false)
        boolean f = true;
        System.out.println(f);
        // arrays is references type
        int[] array = new int[24];
        array[0] = 11;
        System.out.println(array[1]);
        float x = (b + a) * 3;
        System.out.println(x);
        System.out.println(Math.PI);
        f = a > 10;
        System.out.println(f);
        System.out.printf("a = %d, b = %f, f = %s, s = %s", a, b, f, "JAVA");
    }
}