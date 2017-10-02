package com.javarush.task.task18.task1820;

import java.io.*;

/* 
Округление чисел
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        String fn1 = reader.readLine();
        String fn2 = reader.readLine();

        BufferedReader fr = new BufferedReader(new InputStreamReader(new FileInputStream(fn1)));
        BufferedWriter fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fn2)));

        while((line = fr.readLine()) != null)
            for (String str : line.split(" "))
                fw.write(Math.round(Double.parseDouble(str)) + " ");

        fr.close();
        fw.close();
    }
}