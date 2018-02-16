class InheritThread {
    public static void main(String[] args) {
        new ExtThread().start();
        new ExtThread().start();
    }
}

class ExtThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++)
            try {
                Thread.sleep(100);
                System.out.println(getName() + " thread: " + i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
    }
}