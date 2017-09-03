package com.javarush.task.task14.task1402;

/* 
Bingo-2!
*/

public class Solution {
    public static void main(String[] args) {
        Cat cat = new Cat();

        boolean isCat = cat instanceof Cat;
        boolean isMovable = cat instanceof Movable;
        boolean isTom = cat instanceof TomCat;

        if (isCat && isMovable && isTom) System.out.println("Bingo!");
    }

    interface Movable {
    }

    static class Cat implements Movable {
    }

    static class TomCat extends Cat {

    }
}
