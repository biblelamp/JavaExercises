class FieldExample {
    int a;

    public static void main(String[] args) {
        FieldExample field = new FieldExample();
        field.a = 12;
        System.out.println("a = " + field.a);
        System.out.println(field.getA());
        field.printA();
    }

    int getA() {
        return a;
    }

    void printA() {
        System.out.println(a);
    }

}