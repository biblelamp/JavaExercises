import java.util.*;

class ExampleArray {

    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        new ExampleArray().go();
    }

    void go() {
        int max = 100; // maximum value of the array element
        int num = 15; // number of array elements
        int item;

        // create and fill array
        int[] array = new int[num];
        fillArray(array, max, true);
        System.out.println(Arrays.toString(array));

        // sort array
        bubbleSort(array);
        System.out.println(Arrays.toString(array));
        
        // binary search
        System.out.println("Enter a number for seaching:");
        item = scanner.nextInt();
        System.out.println(binarySearch(array, item));
        
    }

    void fillArray(int[] array, int max, boolean alsoNegative) {
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(max);
            if (i % 2 == 0 && alsoNegative) 
                array[i] = -array[i];
        }
    }

    void bubbleSort(int[] array) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < array.length - 1; i++)
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    flag = true;
                }
        }
    }
    
    int binarySearch(int[] array, int item) {
        int first = 0;
        int last = array.length - 1;
        int position = (first + last) / 2;
        while ((array[position] != item) && (first <= last)) {
            if (array[position] > item)
                last = position - 1;
            else
                first = position + 1;
            position = (first + last) / 2;
        }
        return (first <= last)? position : -1;
    }
}