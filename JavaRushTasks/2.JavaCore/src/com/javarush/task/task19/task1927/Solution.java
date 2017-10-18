package com.javarush.task.task19.task1927;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/*
Контекстная реклама
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        // save real PrintStream in special variable
        PrintStream consoleStream = System.out;

        // create dynamic array as output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // create adapter for class PrintStream...
        PrintStream stream = new PrintStream(outputStream);
        // ...and set it as System.out
        System.setOut(stream);

        // call the function
        testString.printSomething();

        // restore the real PrintStream
        System.setOut(consoleStream);

        // convert writing in our ByteArray data to array of string
        String[] str = outputStream.toString().split("\\n");

        // output result strings
        for (int i = 0; i < str.length; i++) {
            if (i % 2 == 0 && i > 0)
                System.out.println("JavaRush - курсы Java онлайн");
            System.out.println(str[i]);
        }
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}