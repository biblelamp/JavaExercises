package lesson16;

public class Lesson16 {
    public static void main(String[] args) {
        Operationable addition = (x, y) -> { return x + y; };
        Operationable substraction = (x, y) -> x - y;

        int a = addition.calculate(5, 5);
        int b = substraction.calculate(8, 3);
        System.out.println(a);
        System.out.println(b);
        // TODO 1. Добавить умножение и деление
        // 2. В занятии 12 добавить лямбды для всех компараторов
        // 3. Прочитать статью (глава 8), попробовать обобщенный функциональный интерфейс
    }
}
