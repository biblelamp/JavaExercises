import java.util.concurrent.Semaphore;

class Parking {
    // парковочное место занято - true, свободно - false
    static boolean[] PARKING_PLACES = new boolean[5];
    // устанавливаем флаг "справедливый", в таком случае метод
    // aсquire() будет раздавать разрешения в порядке очереди
    static final Semaphore SEMAPHORE = new Semaphore(5, true);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 7; i++) {
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
            System.out.printf("Car №%d drove up to the parking lot.\n", carNumber);
            try {
                // acquire() запрашивает доступ к следующему за вызовом этого метода
                // блоку кода, если доступ не разрешен, поток вызвавший этот метод
                // блокируется до тех пор, пока семафор не разрешит доступ
                SEMAPHORE.acquire();

                int parkingNumber = -1;

                // ищем свободное место и паркуемся
                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < 5; i++)
                        if (!PARKING_PLACES[i]) {      // если место свободно
                            PARKING_PLACES[i] = true;  // занимаем его
                            parkingNumber = i;         // наличие свободного места
                            System.out.printf(         // гарантирует семафор 
                                "Car №%d parked in place %d.\n", carNumber, i);
                            break;
                        }
                }

                Thread.sleep(5000);       // уходим за покупками, к примеру

                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingNumber] = false; // освобождаем место
                }

                // release() освобождает ресурс
                SEMAPHORE.release();
                System.out.printf("Car №%d left the parking lot.\n", carNumber);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}