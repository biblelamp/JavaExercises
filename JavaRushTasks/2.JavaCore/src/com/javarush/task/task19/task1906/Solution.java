package com.javarush.task.task19.task1906;

import java.io.*;

/*
Четные символы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();

        reader.close();

        FileReader fileReader = new FileReader(file1);
        FileWriter fileWriter = new FileWriter(file2);

        boolean trigger = false;
        while (fileReader.ready()) {
            int data = fileReader.read();
            if (trigger) fileWriter.write(data);
            trigger = !trigger;
        }

        fileReader.close();
        fileWriter.close();
    }
}