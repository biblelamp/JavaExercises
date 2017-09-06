package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();

        BufferedWriter file = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filename)));

        String line;
        do {
            line = reader.readLine();
            file.write(line);
            file.newLine();
        } while (!line.equals("exit"));

        file.close();
        reader.close();
    }
}