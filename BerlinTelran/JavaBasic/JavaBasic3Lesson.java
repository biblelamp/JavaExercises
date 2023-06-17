class JavaBasic3Lesson {
    public static void main(String[] args) {
        int[] arr = {22, 2, 3, -1, 5, 6, 11, 3};
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        printArray(arr);
    }
    // 22, 2, 3, -1, 5, 6, 11, 3
    // 2, 22, 3, -1 ...
    // -1, 2, 22, 3, ...
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
}