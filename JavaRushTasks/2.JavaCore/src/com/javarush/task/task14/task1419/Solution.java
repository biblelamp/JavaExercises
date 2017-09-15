package com.javarush.task.task14.task1419;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //it's first exception
        try {
            float i = 1 / 0;
        } catch (ArithmeticException e) {
            exceptions.add(e);      // ArithmeticException
        }
        try {
            int num[] = new int[3];
            System.out.println(num[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            exceptions.add(e);     // ArrayIndexOutOfBoundsException
        }
        try {
            int[] a = new int[-1];
        } catch (NegativeArraySizeException e) {
            exceptions.add(e);     // NegativeArraySizeException
        }
        try {
            Object x[] = new String[3];
            x[0] = new Integer(0);
        } catch (ArrayStoreException e) {
            exceptions.add(e);     // ArrayStoreException
        }
        try {
            String str = "Java";
            char c = str.charAt(4);
        } catch (StringIndexOutOfBoundsException e) {
            exceptions.add(e);     // StringIndexOutOfBoundsException
        }
        try {
            Object x = new Integer(0);
            String s = (String) x;
        } catch (ClassCastException e) {
            exceptions.add(e);     // ClassCastException
        }
        try {
            String str = null;
            System.out.println(str.length());
        } catch (NullPointerException e) {
            exceptions.add(e);     // NullPointerException
        }
        try {
            int num = Integer.parseInt("qwe");
        } catch (NumberFormatException e) {
            exceptions.add(e);     // NumberFormatException
        }
        try {
            FileReader fr = new FileReader(new File("undefined.txt"));
        } catch (IOException e) {
            exceptions.add(e);     // FileNotFoundException
        }
        try {
            Class<?> aClass = Class.forName("");
        } catch (ClassNotFoundException e) {
            exceptions.add(e);    // ClassNotFoundException
        }
    }
}