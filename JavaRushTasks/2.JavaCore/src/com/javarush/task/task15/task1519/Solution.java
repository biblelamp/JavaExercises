package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while (true) {
            str = reader.readLine();
            if (str.equals("exit"))
                break;
            try {
                if (str.indexOf(".") > 0) {
                    Double value = Double.parseDouble(str);
                    print(value);
                    continue;
                }
                int value = Integer.parseInt(str);
                if (value > 0 && value < 128) {
                    print((short) value);
                    continue;
                }
                if (value >= 128 || value <= 0) {
                    print(value);
                    continue;
                }
            } catch (NumberFormatException ex) {
            }
            print(str);
        }
    }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}