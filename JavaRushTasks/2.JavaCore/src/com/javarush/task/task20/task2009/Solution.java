package com.javarush.task.task20.task2009;

import java.io.Serializable;

/*
Как сериализовать static?
*/
public class Solution {
    public static class ClassWithStatic implements Serializable {
        public static String staticString = "it's test static string";
        public int i;
        public int j;
    }

    // must read
    // http://javateaching.blogspot.ru/2011/12/serialization-static-field.html

    public static void main(String[] args) {
    }
}