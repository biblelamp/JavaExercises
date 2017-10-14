package com.javarush.task.task19.task1912;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/*
Ридер обертка 2
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

        // convert writing in our ByteArray data to string + replace something
        String result = outputStream.toString().replaceAll("te", "\\?\\?");

        // output result string
        System.out.println(result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}