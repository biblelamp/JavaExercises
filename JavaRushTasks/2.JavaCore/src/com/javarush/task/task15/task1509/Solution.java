package com.javarush.task.task15.task1509;

/* 
Мужчина или женщина?
*/

public class Solution {
    public static void main(String[] args) {
        Man man = new Man();
        Woman woman = new Woman();

        printName(man);
        printName(woman);
    }

    public static void printName(Human human) {
    }

    public static class Human {
    }

    public static class Man extends Human {
    }

    public static class Woman extends Human {
    }
}