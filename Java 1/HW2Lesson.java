/**
 * Java. Level 1. Lesson 2. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated Sep 17, 2018
 */
import java.util.Arrays;
import java.util.Scanner;

class HW2Lesson {

    public static void main(String[] args) {

        // simple tasks (1..4)
        invertArray();
        System.out.println(Arrays.toString(fillArray()));
        System.out.println(Arrays.toString(
            changeArray(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1})));
        fillDiagonal(5);

        // tasks of increased complexity (5..7)
        // find min and max (5)
        System.out.println(Arrays.toString(
            findMinMax(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1})));

        // check balance (6)
        int[][] test = {{1, 1, 1, 2, 1}, {2, 1, 1, 2, 1}, {10, 10}};
        for (int i = 0; i < test.length; i++)
            System.out.println(
                Arrays.toString(test[i]) + " " + checkBalance(test[i]));

        // shift array (7)
        System.out.println(Arrays.toString(
            shiftArray(new int[]{1, 2, 3, 4, 5}, 2)));
        System.out.println(Arrays.toString(
            shiftArray(new int[]{1, 2, 3, 4, 5}, -3)));

        // console calculator (extra)
        calcConsole();
    }

    /**
     * 1. Задать целочисленный массив, состоящий из 0 и 1.
     *    Например: [1, 1, 0, 0, 1, 0, 1, 1, 0, 0]. С помощью цикла
     *    и условия заменить 0 на 1, 1 на 0
     */
    static void invertArray() {
        // init array directly
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        // init array using random()
        //int[] array = new int[10];
        //for (int i = 0; i < array.length; i++)
        //    array[i] = (int)(Math.random() + 0.5);
        System.out.println(Arrays.toString(array));

        // invert
        for (int i = 0; i < array.length; i++)
            array[i] = 1 - array[i];
            //array[i] = array[i] ^ 1;
            //array[i] = (array[i] == 1)? 0 : 1;
        System.out.println(Arrays.toString(array));
    }

    /**
     * 2. Задать пустой целочисленный массив размером 8.
     *    С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21
     */
    static int[] fillArray() {
        int[] array = new int[8];
        for (int i = 0, j = 0; i < array.length; i++, j += 3)
            array[i] = j;
        return array;
    }

    /**
     * 3. Задать массив [1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1] пройти по нему
     *   циклом, и числа меньшие 6 умножить на 2
     */
    static int[] changeArray(int[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i] < 6)
                array[i] *= 2;
        return array;
    }

    /**
     * 4. Создать квадратный двумерный целочисленный массив (количество
     *    строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить
     *    его диагональные элементы единицами
     */
    static void fillDiagonal(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - i - 1] = 1;
        }
        System.out.println(Arrays.deepToString(matrix));
        for (int i = 0; i < size; i++)
            System.out.println(Arrays.toString(matrix[i]));
    }

    /**
     * 5. ** Задать одномерный массив и найти в нём минимальный
     *    и максимальный элементы
     */
    static int[] findMinMax(int[] array) {
        int[] minmax = new int[2];
        minmax[0] = minmax[1] = array[0];
        for (int i = 1; i < array.length; i++) {
            if (minmax[0] > array[i])
                minmax[0] = array[i];
            if (minmax[1] < array[i])
                minmax[1] = array[i];
        }
        return minmax;
    }

    /**
     * 6. ** Написать метод, в который передается не пустой одномерный
     *    целочисленный массив, метод должен вернуть true если в массиве 
     *    есть место, в котором сумма левой и правой части массива равны.
     *    Примеры: 
     *      checkBalance([1, 1, 1, || 2, 1]) →  true
     *      checkBalance([2, 1, 1, 2, 1]) → false
     *      checkBalance([10, || 10]) → true
     *    граница показана символами ||, эти символы в массив не входят
     */
    static boolean checkBalance(int[] array) {
        if (array == null || array.length < 2)
            return false;
        int sumArray = 0, sumLeft = 0;
        for (int i : array)
            sumArray += i;
        if ((sumArray == 0) && (sumArray % 2 != 0)) // by DSerov
            return false;
        for (int i = 0; i < array.length - 1; i++) {
            sumLeft += array[i];
            if (sumLeft*2 == sumArray)
                return true;
        }
        return false;
    }

    /**
     * 7. **** Написать метод, которому на вход подается одномерный массив
     *    и число n (может быть положительным, или отрицательным), при этом
     *    метод должен сместить все элементы массива на n позиций.
     *    Для усложнения задачи нельзя пользоваться вспомогательными массивами
     */
    static int[] shiftArray(int[] array, int shift) {
        if (array == null || array.length < 2
            || shift == 0 || shift % array.length == 0)
            return array;
        shift = shift % array.length; // optimization of the number of shifts
        if (shift < 0)
            shift += array.length; // by Gusar Mikhail
        for (int cnt = 0; cnt < shift; cnt++) {
            int tmp = array[array.length - 1];
            for (int i = array.length - 1; i > 0; i--)
                array[i] = array[i - 1];
            array[0] = tmp;
        }
        return array;
    }

    /**
     * 8. ***** Написать простой консольный калькулятор
     */
    static void calcConsole() {

       Scanner read = new Scanner(System.in);
       System.out.println("Enter separated by space: Integer [+|-|*|/] Integer");

       int first = read.nextInt();
       String operator = read.next();
       int second = read.nextInt();

       switch(operator) {
           case "+":
               System.out.println("= " + (first + second));
               break;
           case "-":
               System.out.println("= " + (first - second));
               break;
           case "*":
               System.out.println("= " + (first * second));
               break;
           case "/":
               if (second != 0)
                   System.out.println("= " + (first / second));
               else
                   System.out.println("Error: Division by zero");
               break;
          default:
               System.out.println("Error: Undefined operation");
       }
   }
}