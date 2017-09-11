package com.javarush.task.task15.task1507;

/* 
ООП - Перегрузка
*/

public class Solution {
    public static void main(String[] args) {
        printMatrix(2, 3, "8");
    }

    public static void printMatrix(int m, int n, String value) { // 1
        System.out.println("Заполняем объектами String");
        printMatrix(m, n, (Object) value);
    }

    public static void printMatrix(int m, int n, Object value) { // 2
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(value);
            }
            System.out.println();
        }
    }

    public static void printMatrix() {                            // 3
        System.out.println("Всё по умолчанию");
    }

    public static void printMatrix(int m, int n) {                // 4
        System.out.println("String по умолчанию");
    }

    public static void printMatrix(int m) {                       // 5
        System.out.println("n и String по умолчанию");
    }

    public static void printMatrix(int m, int n, int k) {         // 6
    }

    public static void printMatrix(int m, int n, boolean b) {     // 7
    }

    public static void printMatrix(double m, double n, String s) {// 8
    }

    public static void printMatrix(double m, double n) {          // 9
    }

    public static void printMatrix(double m) {                    // 10
    }
}