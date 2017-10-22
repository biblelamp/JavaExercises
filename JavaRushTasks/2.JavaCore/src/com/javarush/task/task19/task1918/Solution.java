package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String fileName = reader.readLine();
        reader.close();

        StringBuffer str = new StringBuffer("");
        BufferedReader file = new BufferedReader(new FileReader("file.html"));//fileName));
        while (file.ready())
            str.append(file.readLine() + "\n");
        file.close();

        String tag = "span"; //args[0];
        int counter = 0;
        int start = -1;
        for (int i = 0; i < str.length() - tag.length() - 2; i++) {
            if (str.substring(i, i + tag.length() + 1).
                    equalsIgnoreCase("<" + tag)) { // opening tag
                if ((str.charAt(i + tag.length() + 1) == '>') ||
                    (str.charAt(i + tag.length() + 1) == ' ') ||
                    (str.charAt(i + tag.length() + 1) == '\n')) {
                    counter++;
                    if (counter == 1)
                        start = i;
                }
            }
            if (str.substring(i, i + tag.length() + 3).
                    equalsIgnoreCase("</" + tag + ">")) { // closing tag
                counter--;
                if (counter == 0)
                    System.out.println(str.substring(start, i + tag.length() + 3));
            }
        }
    }
}