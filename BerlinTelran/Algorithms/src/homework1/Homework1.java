package homework1;

/**
 * Algorithms. Homework #1
 *
 * @author Sergey Iryupin
 * @version 22 Jun 2023
 */

/**
 * Тест. Задача №1
 * Посмотрите на схему, сопоставьте номер с его обозначением
 * Ответ: 1С2В3А
 *
 * Задача №2
 * Выберите описание, которое характеризует Сортировку пузырьком (Bubble sorting)
 * Ответ: С
 *
 * Задача №3
 * Алгоритмизация – это…
 * Ответ: А
 *
 * Задача №4
 * Выберите то, что НЕ является характеристикой алгоритма
 * Ответ: D
 *
 * Задача №5
 * Какого алгоритма НЕ существует?
 * Ответ: E
 *
 * Задача №6
 * Посмотрите на картинку и выберите определение для каждого элемента блок-схемы:
 * Ответ: 1B2A3C4D5E
 *
 * Задача №7
 * Дан алгоритм покупки продуктов в текстовом виде, расставьте операции, написанные на псевдокоде в правильном порядке.
 * Ответ: 1B2A3D4C
 */

/**
 * Задача №2
 * Написать псевдокод для алгоритма: сложить три числа и вывести сумму.
 * START
 *   READ a,b,c
 *   d = a + b + c
 *   WRITE d
 * END
 */

/**
 * Задача №3
 * Написать псевдокод для алгоритма линейного поиска
 * START
 *   READ array[], key
 *   FOR i = 0 to i < array.length DO
 *     IF array[i] = key THEN
 *       RETURN true
 *   RETURN false;
 * END
 */

public class Homework1 {
    public static void main(String[] args) {

        // task #2
        int a = 2;
        int b = 5;
        int c = 8;
        int d = a + b + c;
        System.out.println(d);

        // task #3
        int[] array = {6, 12, 5, 22, 4, 87, 7, 18};
        System.out.println(contains(array, 5));
        System.out.println(contains(array, -1));
    }

    static boolean contains(int[] array, int key) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key) {
                return true;
            }
        }
        return false;
    }
}
