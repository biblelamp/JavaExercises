package com.javarush.task.task14.task1420;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
НОД see also good article - https://habrahabr.ru/post/205106/
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(reader.readLine());
        if (a <= 0)
            throw new NumberFormatException();
        int b = Integer.parseInt(reader.readLine());
        if (b <= 0)
            throw new NumberFormatException();

        while (a  != b) // the classical Euclidean algorithm
            if (a > b)
                a = a - b;
            else
                b = b - a;

        System.out.println(a);
    }
}