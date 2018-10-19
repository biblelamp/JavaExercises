// Lesson from Otus
import java.util.concurrent.*;

class AsyncJava {
    int result;

    public static void main(String[] args) 
            throws InterruptedException, ExecutionException {
        AsyncJava aj = new AsyncJava();
        //aj.testFutureOldStyle();
        aj.futureTest();
    }

    public Integer slowInit() {
        System.out.println("started task slowInit()");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public void futureTest()
            throws InterruptedException, ExecutionException {
        Callable<Integer> r = this::slowInit;
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> future = es.submit(r);
        Integer res = future.get();
        System.out.println("futureTest() is finished: " + res);
    }

    public void testFutureOldStyle() throws InterruptedException {
        Thread t = new Thread() {
            public void run() {
                result = slowInit();
            };
        };
        t.start();
        t.join();
        System.out.println("testFutureOldStyle() is finished: " + result);
    }
}
