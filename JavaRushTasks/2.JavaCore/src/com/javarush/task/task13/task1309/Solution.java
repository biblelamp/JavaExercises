package com.javarush.task.task13.task1309;

/* 
Всё, что движется
*/

public class Solution {
    public static void main(String[] args) throws Exception {
    }

    interface Movable {
        Double speed();
    }

    interface Flyable extends Movable {
        Double speed(Flyable fly);
    }
}