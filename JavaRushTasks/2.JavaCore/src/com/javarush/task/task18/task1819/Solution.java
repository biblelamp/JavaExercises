package com.javarush.task.task18.task1819;

import java.io.*;

/*
Объединение файлов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fn1 = reader.readLine();
        String fn2 = reader.readLine();

        FileInputStream f = new FileInputStream(fn1);
        byte[] file1 = new byte[f.available()];
        f.read(file1);
        f.close();

        FileOutputStream f1 = new FileOutputStream(fn1);
        FileInputStream f2 = new FileInputStream(fn2);
        byte[] file2 = new byte[f2.available()];
        f2.read(file2);
        f1.write(file2);
        f1.write(file1);

        f1.close();
        f2.close();
    }
}