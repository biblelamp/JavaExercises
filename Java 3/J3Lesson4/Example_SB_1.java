class Example_SB_1 {
    public static void main(String[] args) {
        Example_SB_1 e1 = new Example_SB_1();
        System.out.println("Start");
        new Thread(() -> e1.method1()).start();
        new Thread(() -> e1.method2()).start();
    }

    synchronized void method1() {
        System.out.println("method1 begin");
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("method1 end");
    }

    synchronized void method2() {
        System.out.println("method2 begin");
        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("method2 end");
    }
}