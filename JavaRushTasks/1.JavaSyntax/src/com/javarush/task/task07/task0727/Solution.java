package com.javarush.task.task07.task0727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Меняем функциональность
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> list = new ArrayList<String>();
        while (true) {
            String s = reader.readLine();
            if (s.isEmpty()) break;
            list.add(s);
        }

        //ArrayList<String> listUpperCase = new ArrayList<String>();
        ArrayList<String> doubleOrTriple = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            //listUpperCase.add(s.toUpperCase());
            doubleOrTriple.add((s.length() % 2 == 0)? s + " " + s : s + " " + s + " " + s);
        }

        //for (int i = 0; i < listUpperCase.size(); i++)
        //    System.out.println(listUpperCase.get(i));

        for (int i = 0; i < doubleOrTriple.size(); i++)
            System.out.println(doubleOrTriple.get(i));
    }
}