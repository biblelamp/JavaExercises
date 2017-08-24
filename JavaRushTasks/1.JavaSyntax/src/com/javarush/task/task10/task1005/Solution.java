package com.javarush.task.task10.task1005;

/* 
Задача №5 на преобразование целых типов
*/

public class Solution {
    public static void main(String[] args) {
        int a = (byte) 44;
        int b = (byte) 300;
        short c = (byte) (b - a);
        System.out.println(c);
    }
}