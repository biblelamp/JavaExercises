class PriorityDemo {

    public static void main(String args[]) { 
        Priority mtl = new Priority("Hi");
        Priority mt2 = new Priority("Lo");

        // set priority
        mtl.thread.setPriority(Thread.MAX_PRIORITY); //Thread.NORM_PRIORITY + 2);
        mt2.thread.setPriority(Thread.MIN_PRIORITY); //Thread.NORM_PRIORITY - 2);

        // starting threads
        mtl.thread.start(); 
        mt2.thread.start(); 
        
        try { 
            mtl.thread.join(); 
            mt2.thread.join(); 
        } catch(InterruptedException ex) { 
            System.out.println("Мain thread is interrupted.");
        }

        System.out.println("\nCounter of High Priority: " + mtl.count);
        System.out.println("Counter of Low Priority: " + mt2.count);
    }
}

class Priority implements Runnable {
    int count; 
    Thread thread;
    
    static boolean stop = false; 
    static String currentName;

    // creating new thread
    Priority(String name) { 
        thread = new Thread(this, name);
        count = 0;
        currentName = name;
    }

    // starting new thread
    public void run() {
        System.out.println(thread.getName() + " is starting...");
        do {
            count++; 
            if (currentName.compareTo(thread.getName()) != 0) {
                currentName = thread.getName(); 
                System.out.print(currentName + " ");
            }
        } while (stop == false && count < 10000000);
        stop = true;
        System.out.println("\n" + thread.getName() + " is complete.");
    }
}