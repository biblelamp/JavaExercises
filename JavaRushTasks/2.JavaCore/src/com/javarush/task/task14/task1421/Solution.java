package com.javarush.task.task14.task1421;

/* 
Singleton
*/
public class Solution {
    public static void main(String[] args) {
        Singleton one = Singleton.getInstance();
        Singleton two = Singleton.getInstance();
        Singleton three = Singleton.getInstance();
        System.out.println(one);
        System.out.println(two);
        System.out.println(three);
    }
}
