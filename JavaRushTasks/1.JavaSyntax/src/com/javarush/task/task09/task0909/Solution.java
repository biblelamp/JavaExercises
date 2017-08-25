package com.javarush.task.task09.task0909;

/* 
Исключение при работе с массивами
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try {

            int[] m = new int[2];
            m[8] = 5;

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
    }
}
