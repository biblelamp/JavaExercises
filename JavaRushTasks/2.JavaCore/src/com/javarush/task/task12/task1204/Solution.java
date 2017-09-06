package com.javarush.task.task12.task1204;

/* 
То ли птица, то ли лампа
*/

public class Solution {
    public static void main(String[] args) {
        printObjectType(new Cat());
        printObjectType(new Bird());
        printObjectType(new Lamp());
        printObjectType(new Cat());
        printObjectType(new Dog());
    }

    public static void printObjectType(Object o) {
        String s = o.getClass().getSimpleName();
        String name = "Неопределено";
        switch (s) {
            case "Cat": name = "Кошка";
                break;
            case "Dog": name = "Собака";
                break;
            case "Bird": name = "Птица";
                break;
            case "Lamp": name = "Лампа";
        }
        System.out.println(name);
    }

    public static class Cat {
    }

    public static class Dog {
    }

    public static class Bird {
    }

    public static class Lamp {
    }
}