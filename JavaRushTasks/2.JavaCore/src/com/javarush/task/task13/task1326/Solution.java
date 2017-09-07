package com.javarush.task.task13.task1326;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Сортировка четных чисел из файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<Integer>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String filename = reader.readLine();

        BufferedReader file = new BufferedReader(
                new InputStreamReader(new FileInputStream(filename)));

        while (file.ready())
            list.add(Integer.parseInt(file.readLine()));

        file.close();
        reader.close();

        Collections.sort(list);
        for (Integer i : list)
            if (i % 2 == 0) System.out.println(i);
    }
}