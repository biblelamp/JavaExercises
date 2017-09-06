package com.javarush.task.task12.task1205;

/* 
Определимся с животным
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getObjectType(new Cow()));
        System.out.println(getObjectType(new Dog()));
        System.out.println(getObjectType(new Whale()));
        System.out.println(getObjectType(new Pig()));
    }

    public static String getObjectType(Object o) {
        String s = o.getClass().getSimpleName();
        String name = "Неизвестное животное";
        switch (s) {
            case "Cow": name = "Корова";
                break;
            case "Dog": name = "Собака";
                break;
            case "Whale": name = "Кит";
                break;
            //case "Pig": name = "Свинья";
        }
        return name;
    }

    public static class Cow {
    }

    public static class Dog {
    }

    public static class Whale {
    }

    public static class Pig {
    }
}