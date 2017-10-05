package com.javarush.task.task19.task1908;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Выделяем числа
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileIn = reader.readLine();
        String fileOut = reader.readLine();
        reader.close();

        BufferedReader fileReader = new BufferedReader(new FileReader(fileIn));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileOut));
        while (fileReader.ready()) {
            String[] words = fileReader.readLine().split(" ");
            for (String str : words)
                if (str.matches("\\d+"))
                    fileWriter.write(str + " ");
        }

        fileReader.close();
        fileWriter.close();
    }
}