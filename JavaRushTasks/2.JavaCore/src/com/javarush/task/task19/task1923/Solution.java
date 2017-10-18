package com.javarush.task.task19.task1923;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        BufferedWriter writer = new BufferedWriter(new FileWriter(args[1]));
        while (reader.ready()) {
            String line = reader.readLine();
            for (String str : line.split(" "))
                if (!str.matches("^\\D*$"))
                    writer.write(str + " ");
        }
        reader.close();
        writer.close();
    }
}