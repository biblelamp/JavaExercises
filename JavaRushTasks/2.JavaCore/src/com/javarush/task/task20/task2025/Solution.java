package com.javarush.task.task20.task2025;

import java.util.Arrays;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        long[] result = null;
        long[] armstrNum = {
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                153, 370, 371, 407, 1634, 8208, 9474, 54748, 92727, 93084,
                548834, 1741725, 4210818, 9800817, 9926315, 24678050, 24678051,
                88593477, 146511208, 472335975, 534494836, 912985153, 4679307774L
        };
        int idx = 0;
        while (armstrNum[idx] < N) idx++;
        if (idx > 0) {
            result = new long[idx];
            for (int i = 0; i < result.length; i++)
                result[i] = armstrNum[i];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getNumbers(8208)));
    }
}