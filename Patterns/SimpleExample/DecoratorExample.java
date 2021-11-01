// from https://java-ru-blog.blogspot.com/2020/02/decorator-pattern-java.html

interface Car {
    int getSpeed();
    int getBaggageWeight();
}

// класс обычного автомобиля
class SimpleCar implements Car {
    private final int speed = 60;
    private final int baggageWeight = 100;

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getBaggageWeight() {
        return baggageWeight;
    }
}

// Чтобы сделать из простого автомобиля скоростной спортивный автомобиль у нас есть класс-декоратор SportCar, который в конструкторе принимает класс SimpleCar и добавляет скорости обычному автомобилю.
class SportCar implements Car {
    private final Car car;

    public SportCar(Car car){
        this.car = car;
    }

    @Override
    public int getSpeed() {
        return car.getSpeed() + 150;
    }

    @Override
    public int getBaggageWeight() {
        return car.getBaggageWeight();
    }
}

// Также чтобы увеличить грузоподъемность простого автомобиля у нас есть класс-декоратор Truck, который в конструкторе принимает класс SimpleCar и добавляет грузоподъемности обычному автомобилю.
class Truck implements Car {
    private final Car car;

    public Truck(Car car){
        this.car = car;
    }

    @Override
    public int getSpeed() {
        return car.getSpeed();
    }

    @Override
    public int getBaggageWeight() {
        return car.getBaggageWeight() + 1000;
    }
}

// Превращаем обычный автомобиль SimpleCar с помощью декораторов в спортивный автомобиль или грузовик.
class DecoratorExample {
    public static void main(String[] args) {
        Car simpleCar = new SimpleCar();
        System.out.println("Speed of SimpleCar: " + simpleCar.getSpeed());
        System.out.println("SimpleCar has baggage weight: " + String.valueOf(simpleCar.getBaggageWeight()));

        Car sportCar = new SportCar(simpleCar);
        System.out.println("Speed of SportCar: " + sportCar.getSpeed());
        System.out.println("SporCar has baggage weight: " + sportCar.getBaggageWeight());

        Car truck = new Truck(simpleCar);
        System.out.println("Speed of truck: " + truck.getSpeed());
        System.out.println("Truck has baggage weight: " + truck.getBaggageWeight());
    }
}