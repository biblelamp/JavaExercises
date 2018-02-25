/* В гонке принимают участие 5 автомобилей.
 * Для начала гонки нужно, чтобы выполнились следующие условия:
 * - Каждый из пяти автомобилей подъехал к стартовой прямой;
 * - Была дана команда «На старт!»;
 * - Была дана команда «Внимание!»;
 * - Была дана команда «Марш!».
 * Важно, чтобы все автомобили стартовали одновременно.
 */
import java.util.concurrent.CountDownLatch;

class Race {
    static final int NUMBER_OF_CARS = 5;
    // создаем CountDownLatch со счётчиком == 8
    static final CountDownLatch START = new CountDownLatch(NUMBER_OF_CARS + 3);
    // условная длина гоночной трассы
    static final int trackLength = 500000;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= NUMBER_OF_CARS; i++) {
            new Thread(new Car(i, (int)(Math.random() * 100 + 50))).start();
            Thread.sleep(1000);
        }

        while (START.getCount() > 3) // проверяем: собрались ли все автомобили
            Thread.sleep(100);       // у стартовой прямой? Если нет - ждем 100ms

        Thread.sleep(1000);
        System.out.println("On your marks!");
        START.countDown(); // команда дана, уменьшаем счетчик на 1
        Thread.sleep(1000);
        System.out.println("Attention!");
        START.countDown(); // команда дана, уменьшаем счетчик на 1
        Thread.sleep(1000);
        System.out.println("Go!");
        START.countDown(); // команда дана, уменьшаем счетчик на 1
        // счетчик становится равным нулю, и все ожидающие потоки
        // одновременно разблокируются
    }

    static class Car implements Runnable {
        private int carNumber;
        private int carSpeed; // скорость автомобиля постоянная

        Car(int carNumber, int carSpeed) {
            this.carNumber = carNumber;
            this.carSpeed = carSpeed;
        }

        @Override
        public void run() {
            try {
                System.out.printf(
                    "Car №%d (%d) drove up to the starting line.\n",
                    carNumber, carSpeed);
                // aвтомобиль подъехал к стартовой прямой - условие выполнено
                // уменьшаем счетчик на 1
                START.countDown();
                // метод await() блокирует поток, вызвавший его, до тех пор
                // пока счетчик CountDownLatch не станет равен 0
                START.await();
                Thread.sleep(trackLength / carSpeed); // ждём (проезжаем трассу)
                System.out.printf("Car №%d finished!\n", carNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
             }
        }
    }
}