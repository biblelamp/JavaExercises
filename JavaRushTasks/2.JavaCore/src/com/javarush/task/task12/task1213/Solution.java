package com.javarush.task.task12.task1213;

/* 
«Исправь код», часть 2
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static abstract class Pet {
        public String getName() {
            return "Я - котенок";
        }

        public abstract Pet getChild();
    }
}