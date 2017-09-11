package com.javarush.task.task15.task1520;

/* 
Тренировка мозга
*/

public class Solution {
    public static void main(String[] args) {
        Duck duck = new Duck();
        Util.fly(duck);
        Util.move(duck);
    }

    public static class Duck implements Flyable, Movable {
        @Override
        public void doAction() {
            System.out.println("flying");
        }

        @Override
        public void doAnotherAction() {
            System.out.println("moving");
        }
    }

    public static class Util {
        static void fly(Flyable animal) {
            animal.doAction();
        }

        static void move(Movable animal) {
            animal.doAnotherAction();
        }
    }

    public static interface Flyable {
        void doAction();
    }

    public static interface Movable {
        void doAnotherAction();
    }
}