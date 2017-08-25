package com.javarush.task.task10.task1003;

/* 
Задача №3 на преобразование целых типов
*/

public class Solution {
    public static void main(String[] args) {
        float f = (float) 128.50;
        int i = (byte) f; // was (int)
        int b = (int) (i + f);

        System.out.println(b);
    }
}
