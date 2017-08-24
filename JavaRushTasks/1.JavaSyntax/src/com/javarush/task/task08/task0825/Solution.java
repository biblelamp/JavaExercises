package com.javarush.task.task08.task0825;

/* 
Модификатор запутался
*/

public class Solution {
    public static int A = 5;
    public static int B = 2;

    public static int C = A * B;
    public int D = B * A;

    public static void main(String[] args) {
    }

    public int getValue() {
        return D;
    }

    public int getValue2() {
        return C;
    }
}

