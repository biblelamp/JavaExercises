class MethodStatic {
    public static void main(String[] args) {
        int c = add(5, 6);
        System.out.println("5 + 6 = " + c);
    }

    static int add(int a, int b) {
        return a + b;
    }
}