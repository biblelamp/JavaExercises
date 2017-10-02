package com.javarush.task.task18.task1818;

import java.io.*;

/*
Два в одном
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fn1 = reader.readLine();
        String fn2 = reader.readLine();
        String fn3 = reader.readLine();

        FileOutputStream f1 = new FileOutputStream(fn1);
        FileInputStream f2 = new FileInputStream(fn2);
        FileInputStream f3 = new FileInputStream(fn3);

        {
            byte[] buffer = new byte[f2.available()];
            f2.read(buffer);
            f1.write(buffer);
        }

        {
            byte[] buffer = new byte[f3.available()];
            f3.read(buffer);
            f1.write(buffer);
        }

        f1.close();
        f2.close();
        f3.close();
    }
}