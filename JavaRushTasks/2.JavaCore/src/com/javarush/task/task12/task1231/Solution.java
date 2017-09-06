package com.javarush.task.task12.task1231;

/* 
Ненужные абстракции
*/

public class Solution {
    public static void main(String[] args) {
        Horse horse = new Pegas();
        horse.run();
    }

    public static interface Fly {
        public abstract void fly();
    }

    public static class Horse {
        public void run() {
        }
    }

    public static class Pegas extends Horse implements Fly {
        public void fly() {
        }
    }

    public static abstract class SwimPegas extends Pegas {
        public abstract void swim();
    }
}