class MethodNotStatic {
    public static void main(String[] args) {
        MethodNotStatic method = new MethodNotStatic();
        int c = method.add(5, 6);
        System.out.println("5 + 6 = " + c);
    }

    int add(int a, int b) {
        return a + b;
    }
}