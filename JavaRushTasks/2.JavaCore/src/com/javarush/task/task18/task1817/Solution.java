package com.javarush.task.task18.task1817;

import java.io.FileInputStream;
import java.io.IOException;

/*
Пробелы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int countSpaces = 0;
        FileInputStream file = new FileInputStream(args[0]);
        int countChars = file.available();
        while (file.available() > 0) {
            if ((byte) file.read() == ' ')
                countSpaces++;
        }
        file.close();
        System.out.printf("%.2f", (double) countSpaces/countChars*100);
    }
}