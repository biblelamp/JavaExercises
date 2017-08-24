package com.javarush.task.task04.task0401;

/* 
Мне не подходит этот возраст…
*/
public class Solution {
    public static void main(String[] args) {

        Person person = new Person();
        System.out.println("Age is: " + person.age);
        person.adjustAge(person.age);
        System.out.println("Adjusted Age is: " + person.age);
    }

    public static class Person {
        public int age = 20;

        public void adjustAge(int age) {
            age = age + 20;
            System.out.println("The Age in adjustAge() is " + age);
        }
    }
}
