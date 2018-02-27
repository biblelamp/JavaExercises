/* Паромная переправа. Паром может переправлять одновременно
 * по 3 автомобиля. Чтобы не гонять паром лишний раз, нужно отправлять его,
 * когда у переправы соберется минимум 3 автомобиля.
 */
import java.util.concurrent.CyclicBarrier;

class Ferry {
    static final int NUMBER_OF_CARS = 3;
    // инициализируем барьер на 3 потока + task, который будет выполняться,
    // когда у барьера соберется 3 потока, после чего они будут освобождены
    static final CyclicBarrier BARRIER =
        new CyclicBarrier(NUMBER_OF_CARS, new FerryBoat());

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 9; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    // task, который будет выполняться при достижении барьера
    static class FerryBoat implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(500);
                System.out.println("Ferry ferrying cars!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // cтороны, которые будут достигать барьера
    static class Car implements Runnable {
        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                System.out.printf("Car №%d drove up to the ferry.\n", carNumber);
                // для указания потоку о том что он достиг барьера, нужно вызвать
                // метод await(), после этого данный поток блокируется, и ждет
                // пока остальные достигнут барьера
                BARRIER.await();
                System.out.printf("Car №%d continued to move.\n", carNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}