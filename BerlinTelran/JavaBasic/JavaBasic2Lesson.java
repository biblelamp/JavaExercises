class JavaBasic2Lesson {
    public static void main(String[] args) {
        printHello();
        printHelloName("world");
        printHelloName("Java");
        printHelloName("Kolya");
        int s = add(3, 5);
        System.out.println(s);
        System.out.println(add(4, 6));
        System.out.println(canWalk(...)? "can walk" : "cannot walk");
    }

    static void printHello() {
        System.out.println("Hello");
        return;
    }

    static void printHelloName(String name) {
        System.out.println("Hello, " + name + "!");
    }

    static int add(int a, int b) {
        int c = a + b;
        return c;
    }
}