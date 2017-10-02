package com.javarush.task.task18.task1824;

import java.io.*;

/*
Файлы и исключения
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fis = null;
        while (true) {
            String filename = reader.readLine();
            try {
                fis = new FileInputStream(filename);
                fis.close();
            } catch (FileNotFoundException ex) {
                System.out.println(filename);
                break;
            }
        }
    }
}