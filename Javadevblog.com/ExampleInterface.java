class ExampleInterface implements InterfaceA, InterfaceB, InterfaceC {

    @Override
    public void doSomething() {
        System.out.println("doSomething is implemented in a particular class");
    }

    public static void main(String[] args) {
        InterfaceA objA = new ExampleInterface();
        InterfaceB objB = new ExampleInterface();
        InterfaceC objC = new ExampleInterface();

        // call methods with specific implementation
        objA.doSomething();
        objB.doSomething();
        objC.doSomething();
    }
}

interface InterfaceA {
    public void doSomething();
}

interface InterfaceB {
    public void doSomething();
}

interface InterfaceC extends InterfaceA, InterfaceB {
    public void doSomething();
}