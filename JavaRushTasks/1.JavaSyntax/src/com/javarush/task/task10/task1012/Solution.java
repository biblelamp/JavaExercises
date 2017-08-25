package com.javarush.task.task10.task1012;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Количество букв
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // алфавит
        String abc = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        char[] abcArray = abc.toCharArray();

        ArrayList<Character> alphabet = new ArrayList<Character>();
        for (int i = 0; i < abcArray.length; i++) {
            alphabet.add(abcArray[i]);
        }

        // ввод строк
        StringBuilder str = new StringBuilder("");
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            list.add(s.toLowerCase());
            str = str.append(list.get(i));
        }

        // напишите тут ваш код
        for (int i = 0; i < 33; i++) {
            int count = 0;
            for (int j = 0; j < str.length(); j++)
                if (str.charAt(j) == alphabet.get(i))
                    count++;
            System.out.println(alphabet.get(i) + " " + count);
        }
    }
}