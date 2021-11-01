// from https://habr.com/ru/post/465835/

interface CarsFactory {
    Sedan createSedan();
    Coupe createCoupe();
}

interface Sedan {
}

interface Coupe {
}

class ToyotaFactory implements CarsFactory {
    @Override
    public Sedan createSedan() {
        return new ToyotaSedan();
    }

    @Override
    public Coupe createCoupe() {
        return new ToyotaCoupe();
    }
}

class FordFactory implements CarsFactory {
    @Override
    public Sedan createSedan() {
        return new FordSedan();
    }

    @Override
    public Coupe createCoupe() {
        return new FordCoupe();
    }
}

class ToyotaCoupe implements Coupe {
    public ToyotaCoupe() {
        System.out.println("Create ToyotaCoupe");
    }
}

class ToyotaSedan implements Sedan {
    public ToyotaSedan() {
        System.out.println("Create ToyotaSedan");
    }
}

class FordCoupe implements Coupe {
    public FordCoupe () {
        System.out.println("Create FordCoupe");
    }
}

class FordSedan implements Sedan {
    public FordSedan() {
        System.out.println("Create FordSedan");
    }
}

public class AbstractFactoryCarExample {
    public static void main(String[] args) {
        CarsFactory factory;

        factory = new ToyotaFactory();
        factory.createSedan();

        factory = new FordFactory();
        factory.createCoupe();
    }
}