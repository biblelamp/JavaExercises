package com.javarush.task.task12.task1218;

/* 
Есть, летать и двигаться
*/

public class Solution {
    public static void main(String[] args) {

    }

    public interface Fly {
        public void fly();
    }

    public interface Move {
        public void move();
    }

    public interface Eat {
        public void eat();
    }

    public class Dog implements Move, Eat {
        public void move() {}
        public void eat() {}
    }

    public class Duck implements Fly, Move, Eat{
        public void fly() {}
        public void move() {}
        public void eat() {}
    }

    public class Car implements Move {
        public void move() {}
    }

    public class Airplane implements Fly, Move {
        public void fly() {}
        public void move() {}
    }
}