package com.javarush.task.task18.task1821;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

/*
Встречаемость символов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<Character, Integer> tm = new TreeMap<Character, Integer>();

        FileInputStream file = new FileInputStream(args[0]);

        while (file.available() > 0) {
            char ch = (char) file.read();
            Integer value = tm.get(ch);
            tm.put(ch, (value == null)? 1 : value + 1);
        }
        file.close();

        for (Map.Entry<Character, Integer> item : tm.entrySet())
            System.out.println(item.getKey() + " " + item.getValue());
    }
}