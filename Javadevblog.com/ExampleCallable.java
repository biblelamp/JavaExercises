import java.util.*;
import java.util.concurrent.*;
 
class ExampleCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return Thread.currentThread().getName();
    }

    public static void main(String args[]){
        // Получаем ExecutorService утилитного класса Executors с размером пула потоков равному 10
        ExecutorService executor = Executors.newFixedThreadPool(10);
        // создаем список с Future, которые ассоциированы с Callable
        List<Future<String>> list = new ArrayList<Future<String>>();
        // создаем экземпляр ExampleCallable
        Callable<String> callable = new ExampleCallable();
        for(int i=0; i < 100; i++) {
            // сабмитим Callable таски, которые будут 
            // выполнены пулом потоков
            Future<String> future = executor.submit(callable);
            // добавляя Future в список, 
            // мы сможем получить результат выполнения
            list.add(future);
        }
        for(Future<String> future : list) {
            try {
                // печатаем в консоль возвращенное значение Future
                // будет задержка в 1 секунду, потому что Future.get()
                // ждет пока таск закончит выполнение
                System.out.println(new Date()+ "::" + future.get());
            } catch (InterruptedException | ExecutionException ex) {
                ex.printStackTrace();
            }
        }
        executor.shutdown();
    }
}