interface AbstractFactory {
    AbstractProductA createProductA();
    AbstractProductB createProductB();
}

interface AbstractProductA {
    void interact(AbstractProductB b);
}

interface AbstractProductB {
    void interact(AbstractProductA a);
}

class Client {
    private AbstractProductA productA;
    private AbstractProductB productB;

    Client(AbstractFactory factory) {
        productA = factory.createProductA();
        productB = factory.createProductB();
    }

    void execute() {
        productB.interact(productA);
    }
}

class ConcreteFactory1 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}

class ConcreteFactory2 implements AbstractFactory {
    @Override
    public AbstractProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createProductB() {
        return new ProductB2();
    }
}

class ProductA1 implements AbstractProductA {
    @Override
    public void interact(AbstractProductB b) {
        System.out.println(this.getClass().getName() + " interacts with " + b.getClass().getName());
    }
}

class ProductB1 implements AbstractProductB {
    @Override
    public void interact(AbstractProductA a) {
        System.out.println(this.getClass().getName() + " interacts with " + a.getClass().getName());
    }
}

class ProductA2 implements AbstractProductA {
    @Override
    public void interact(AbstractProductB b) {
        System.out.println(this.getClass().getName() + " interacts with " + b.getClass().getName());
    }
}

class ProductB2 implements AbstractProductB {
    @Override
    public void interact(AbstractProductA a) {
        System.out.println(this.getClass().getName() + " interacts with " + a.getClass().getName());
    }
}

public class AbstractFactoryExample {
    public static void main(String[] args) {
        AbstractFactory factory1 = new ConcreteFactory1();
        Client client1 = new Client(factory1);
        client1.execute();

        AbstractFactory factory2 = new ConcreteFactory2();
        Client client2 = new Client(factory2);
        client2.execute();
    }
}