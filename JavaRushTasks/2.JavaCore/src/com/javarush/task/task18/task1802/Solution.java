package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file = new FileInputStream(reader.readLine());
        int min = Integer.MAX_VALUE;
        while (file.available() > 0) {
            int b = file.read();
            if (b < min)
                min = b;
        }
        file.close();
        System.out.println(min);
    }
}