/**
 * Класс конкретной стратегии должен реализовать этот интерфейс
 * Класс контекста использует интерфейс для конкретной стратегии
 */
interface Strategy {
    int execute(int a, int b); 
}

// Алгоритм сложения реализует интерфейс Strategy
class StrategyAdd implements Strategy {
    public int execute(int a, int b) {
        System.out.println("Called StrategyAdd's execute()");
        return a + b;
    }
}

// Алгоритм вычитания реализует интерфейс Strategy
class StrategySubtract implements Strategy {
    public int execute(int a, int b) {
        System.out.println("Called StrategySubtract's execute()");
        return a - b;
    }
}

// Алгоритм умножения реализует интерфейс Strategy
class StrategyMultiply implements Strategy {
    public int execute(int a, int b) {
        System.out.println("Called StrategyMultiply's execute()");
        return a * b;
    }
}

// Класс Context использует интерфейс Strategy
class Context {
 
    private Strategy strategy;

    // Set new strategy
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    // Execute strategy
    public int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}

// Класс для тестирования работы
class StrategyExample {

    public static void main(String[] args) {
        Context context = new Context();

        context.setStrategy(new StrategyAdd());
        int resultA = context.executeStrategy(3,4);

        context.setStrategy(new StrategySubtract());
        int resultB = context.executeStrategy(3,4);

        context.setStrategy(new StrategyMultiply());
        int resultC = context.executeStrategy(3,4);

        System.out.println("Result A : " + resultA);
        System.out.println("Result B : " + resultB);
        System.out.println("Result C : " + resultC);
    }
}