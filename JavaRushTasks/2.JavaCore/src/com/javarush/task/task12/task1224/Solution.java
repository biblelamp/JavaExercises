package com.javarush.task.task12.task1224;

/* 
Неведома зверушка
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getObjectType(new Cat()));
        System.out.println(getObjectType(new Tiger()));
        System.out.println(getObjectType(new Lion()));
        System.out.println(getObjectType(new Bull()));
        System.out.println(getObjectType(new Pig()));
    }

    public static String getObjectType(Object o) {
        String s = o.getClass().getSimpleName();
        String name = "хз";
        switch (s) {
            case "Cat": name = "Кот";
                break;
            case "Tiger": name = "Тигр";
                break;
            case "Lion": name = "Лев";
                break;
            case "Bull": name = "Бык";
        }
        return name;
    }

    public static class Cat {
    }

    public static class Tiger {
    }

    public static class Lion {
    }

    public static class Bull {
    }

    public static class Pig {
    }
}