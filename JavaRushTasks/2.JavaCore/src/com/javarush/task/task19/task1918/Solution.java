package com.javarush.task.task19.task1918;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
Знакомство с тегами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        StringBuffer str = new StringBuffer("");
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        while (file.ready())
            str.append(file.readLine());
        file.close();

        String tag = args[0];
        LinkedList<Integer> startTag = new LinkedList<Integer>();
        LinkedList<Integer> endTag = new LinkedList<Integer>();

        for (int i = 0; i < str.length() - tag.length() - 2; i++) {

            if (str.substring(i, i + tag.length() + 1).
                    equalsIgnoreCase("<" + tag)) // opening tag
                startTag.add(i);

            if (str.substring(i, i + tag.length() + 3).
                    equalsIgnoreCase("</" + tag + ">")) { // closing tag
                endTag.add(i + tag.length() + 3);
                if (startTag.size() == endTag.size())
                    for (int j = 0; j < startTag.size() + 1; j++)
                        System.out.println(str.substring(startTag.removeFirst(), endTag.removeLast()));
            }
        }
    }
}