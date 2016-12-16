class ExampleThreadWaitNotify {

    public static void main(String[] args) {
        Message msg = new Message("process");
        Waiter waiter = new Waiter(msg);
        new Thread(waiter, "waiter").start();

        Waiter waiter1 = new Waiter(msg);
        new Thread(waiter1, "waiter1").start();

        Notifier notifier = new Notifier(msg);
        new Thread(notifier, "notifier").start();
        System.out.println("Started all threads");
    }
}

class Waiter implements Runnable {
    private Message msg;

    Waiter(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        synchronized (msg) {
            try {
                System.out.println(name + " waiting for a call notify: " + System.currentTimeMillis());
                msg.wait();
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println(name + " method notify was called: " + System.currentTimeMillis());
            System.out.println(name + " : " + msg.getMsg());
        }
    }
}

class Notifier implements Runnable {
    private Message msg;

    Notifier(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " started");
        try {
            Thread.sleep(1000);
            synchronized (msg) {
                msg.setMsg(name + " thread Notifier worked");
                //msg.notify();
                msg.notifyAll();
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

class Message {
    private String msg;

    Message(String msg) {
        this.msg = msg;
    }

    String getMsg() {
        return msg;
    }
 
    void setMsg(String msg) {
        this.msg = msg;
    }
 
}