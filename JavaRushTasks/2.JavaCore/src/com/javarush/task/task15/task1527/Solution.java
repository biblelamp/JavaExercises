package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String url = br.readLine();
        String u1 = url.substring(url.indexOf('?') + 1);
        String value = "";

        for (String temp : u1.split("&")) {
            String[] couple = temp.split("=");
            list.add(couple[0]);
            if (couple[0].equals("obj"))
                value = couple[1];
        }

        for (String a : list)
            System.out.print(a + " ");
        System.out.println();

        if (value != "")
            try {
                alert(Double.parseDouble(value));
            } catch (Exception e) {
                alert(value);
            }
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}