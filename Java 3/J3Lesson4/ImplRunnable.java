class ImplRunnable {
    public static void main(String[] args) {
        new Thread(new RunnableClass()).start();
        new Thread(new RunnableClass()).start();
    }
}

class RunnableClass implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++)
            try {
                Thread.sleep(100);
                System.out.println(
                    Thread.currentThread().getName() + " thread: " + i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    }
}