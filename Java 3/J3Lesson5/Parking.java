/* Существует парковка, которая одновременно может вмещать не более
 * 5 автомобилей. Если парковка заполнена полностью, то вновь прибывший
 * автомобиль должен ждать пока не освободится хотя бы одно место.
 */
import java.util.concurrent.Semaphore;

class Parking {
    static final int PARKING_SIZE = 5;
    static final boolean[] PARKING_PLACES = new boolean[PARKING_SIZE];
    // создаём Семафор с количеством разрешений == PARKING_SIZE и флагом
    // "справедливость", в этом случае метод aсquire() будет
    // раздавать разрешения в порядке очереди
    static final Semaphore SEMAPHORE = new Semaphore(PARKING_SIZE, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i < 11; i++) {
            new Thread(new Car(i)).start();
            Thread.sleep(400);
        }
    }

    static class Car implements Runnable {
        private int carNumber;

        Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            System.out.printf(
                "Car №%d drove up to the parking lot.\n", carNumber);
            try {
                // acquire() запрашивает доступ к следующему за вызовом этого
                // метода блоку кода, если доступ не разрешен, поток вызвавший
                // этот метод блокируется, пока семафор не разрешит доступ
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                // ищем свободное место и паркуемся
                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++)
                        if (!PARKING_PLACES[i]) {      // если место свободно
                            PARKING_PLACES[i] = true;  // занимаем его
                            parkingNumber = i;         // наличие свободного места
                            System.out.printf(         // гарантирует семафор 
                                "Car №%d parked in place %d.\n", carNumber, i + 1);
                            break;
                        }
                }

                if (parkingNumber == -1)
                    System.out.printf(
                        "Car %d didn't find an empty parking lot.\n", carNumber);
                else {
                    Thread.sleep(5000); // уходим за покупками, приходим, уезжаем

                    synchronized (PARKING_PLACES) {
                        PARKING_PLACES[parkingNumber] = false; // освобождаем место
                    }

                    SEMAPHORE.release(); // освобождаем ресурс
                    System.out.printf(
                        "Car №%d left the parking lot.\n", carNumber);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}