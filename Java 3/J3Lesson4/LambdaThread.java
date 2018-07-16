class LambdaThread {
    public static void main(String[] args) {
        new Thread(() -> {method();}).start();
        new Thread(() -> {method();}).start();
    }

    static void method() {
        for (int i = 0; i < 5; i++)
            try {
                Thread.sleep(100);
                System.out.println("new thread: " + i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    }
}