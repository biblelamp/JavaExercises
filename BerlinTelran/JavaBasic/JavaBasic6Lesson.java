class JavaBasic6Lesson {
    public static void main(String[] args) {
        int[] arr = {1, 22, 333, 4444, 55555};
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}; // new int[3][3];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}