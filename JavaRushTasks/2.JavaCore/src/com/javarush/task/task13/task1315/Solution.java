package com.javarush.task.task13.task1315;

/* 
Том, Джерри и Спайк
*/

public class Solution {
    public static void main(String[] args) {
    }

    public interface Movable { // может двигаться
        void move();
    }

    public interface Eatable { // может быть съеден
        void eaten();
    }

    public interface Eat { // может кого-нибудь съесть
        void eat();
    }

    class Cat implements Movable, Eatable, Eat {
        @Override
        public void eat() { }
        @Override
        public void eaten() { }
        @Override
        public void move() { }
    }

    class Mouse implements Movable, Eatable {
        @Override
        public void eaten() { }
        @Override
        public void move() { }
    }

    class Dog implements Movable, Eat {
        @Override
        public void eat() { }
        @Override
        public void move() { }
    }
}