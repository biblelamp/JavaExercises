/**
 * Java. Level 1. Lesson 2. Example of homework
 *
 * @author Sergey Iryupin
 * @version dated 20 Feb 2017
 */
import java.util.*;

public class HW2Lesson {

    public static void main(String[] args) {
        invertArray();
        fillArray();
        processArray(new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1});
        fillMatrix(5);
    }

    /**
     * 1. Задать целочисленный массив, состоящий из 0 и 1.
     *    Например: [1, 1, 0, 0, 1, 0, 1, 1, 0, 0]. С помощью цикла
     *    и условия заменить 0 на 1, 1 на 0
     */
    static void invertArray() {
        //int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        int[] array = new int[10];
        for (int i = 0; i < array.length; i++)
            array[i] = (int)(Math.random() + 0.5);
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++)
            array[i] = (array[i] == 1)? 0 : 1;
            //array[i] = 1 - array[i];
        System.out.println(Arrays.toString(array));
    }

    /**
     * 2. Задать пустой целочисленный массив размером 8.
     *    С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21
     */
    static void fillArray() {
        int[] array = new int[8];
        for (int i = 0, k = 0; i < array.length; i++, k += 3)
            array[i] = k;
        System.out.println(Arrays.toString(array));
    }

    /**
     * 3. Задать массив [1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1] пройти по нему циклом,
     *    и числа меньшие 6 умножить на 2
     */
    static void processArray(int[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i] < 6)
                array[i] *= 2;
        System.out.println(Arrays.toString(array));
    }
    
    /**
     * 4. Создать квадратный двумерный целочисленный массив (количество
     *    строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить
     *    его диагональные элементы единицами
     */
    static void fillMatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            matrix[i][i] = 1;
            matrix[i][size - i - 1] = 1;
        }
        for (int i = 0; i < size; i++)
            System.out.println(Arrays.toString(matrix[i]));
    }
}