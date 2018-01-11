class InheritanceTesting {
    public static void main(String[] args) {
        B b = new B();
        b.setA(5);
        System.out.println(b.getA());
    }
}

class A {
    private int a;
    protected int getA() {
        return a;
    }
    protected void setA(int a) {
        this.a = a;
    }
}

class B extends A { }