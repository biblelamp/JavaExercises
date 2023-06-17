/**
 * Java Basic. Home work #3
 *
 * @author Sergey
 * @todo 12.9.2022
 * @date 14.9.2022
 */
class JavaBasic3Hw {
    public static void main(String[] args) {
        // task 1
        int[] array = {51, 17, 22, -4, 0, 12};
        printArray(array);

        // task 2
        int max = getMax(array);
        System.out.println("Max = " + max);

        // task 3
        int[] invArray = {1, 0, 0, 1, 1, 0, 1, 0, 0};
        printArray(invArray);
        printArray(invertArray(invArray));

        // task 4,5
        int[] sortedArray = bubbleSort(array, true);
        printArray(sortedArray);
        printArray(bubbleSort(array, false));
    }

    static void printArray(int[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    static int[] invertArray(int[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            //result[i] = 1 - array[i];
            if (array[i] == 1) {
                result[i] = 0;
            } else {
                result[i] = 1;
            }
            //result[i] = array[i] == 1? 0 : 1;
        }
        return result;
    }

    static int[] bubbleSort(int[] array, boolean asc) {
        int[] result = new int[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        for (int i = result.length-1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if ((result[j] > result[j + 1] && asc)
                    || (result[j] < result[j + 1] && !asc)) {
                    int tmp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = tmp;
                }
            }
        }
        return result;
    }
}