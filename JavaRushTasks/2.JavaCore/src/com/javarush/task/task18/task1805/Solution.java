package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;
import java.util.Map;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> hm = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream file = new FileInputStream(reader.readLine());
        while (file.available() > 0) {
            int b = file.read();
            Integer value = hm.get(b);
            hm.put(b, ((value == null)? 1 : value + 1));
        }
        file.close();

        for (Map.Entry<Integer, Integer> entry : hm.entrySet())
            System.out.print(entry.getKey() + " ");
    }
}