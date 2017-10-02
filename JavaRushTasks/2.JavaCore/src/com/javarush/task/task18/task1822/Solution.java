package com.javarush.task.task18.task1822;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Поиск данных внутри файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        String f = reader.readLine();
        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(f)));

        while((line = fr.readLine()) != null)
            if (line.startsWith(args[0]))
                System.out.println(line);

        fr.close();
    }
}