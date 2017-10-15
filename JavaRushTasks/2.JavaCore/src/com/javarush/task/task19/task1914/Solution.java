package com.javarush.task.task19.task1914;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/*
Решаем пример
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

        // convert writing in our ByteArray data to string + make arithmetic action
        String str = outputStream.toString().replaceAll("\\p{Cntrl}", "");
        String[] parts = str.split(" ");
        int a = Integer.parseInt(parts[0]);
        int b = Integer.parseInt(parts[2]);
        int result = 0;
        switch (parts[1]) {
            case "+": result = a + b;
                break;
            case "-": result = a - b;
                break;
            case "*": result = a * b;
                break;
        }

        // output result string
        System.out.println(str + result);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}