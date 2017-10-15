package com.javarush.task.task19.task1915;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;


/*
Дублируем текст
*/

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

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

        // convert writing in our ByteArray data to string
        String str = outputStream.toString();

        // output result string
        System.out.println(str);

        // write string to file
        FileOutputStream file = new FileOutputStream(fileName);
        file.write(str.getBytes());
        file.close();
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}