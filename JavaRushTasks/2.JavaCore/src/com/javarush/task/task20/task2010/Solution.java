package com.javarush.task.task20.task2010;

/* 
Как сериализовать что-то свое?
*/
public class Solution {
    public static class Object {
        public String string1;
        public String string2;
    }

    public static int countStrings;

    public static class String {
        private final int number;

        public String() {
            number = ++countStrings;
        }

        public void print() {
            System.out.println("string #" + number);
        }
    }

    public static void main(String[] args) {

    }
}
