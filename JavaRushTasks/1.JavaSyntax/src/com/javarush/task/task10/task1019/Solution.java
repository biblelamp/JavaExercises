package com.javarush.task.task10.task1019;

import java.io.*;

/* 
Функциональности маловато!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int id = Integer.parseInt(reader.readLine());
        String name = reader.readLine();

        System.out.println("Id=" + id + " Name=" + name);
    }
}
