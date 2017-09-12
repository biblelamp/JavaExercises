package com.javarush.task.task15.task1525;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Файл в статическом блоке
*/

public class Solution {
    public static List<String> lines = new ArrayList<String>();

    static {
        try {
            BufferedReader file = new BufferedReader(
                    new InputStreamReader(new FileInputStream(Statics.FILE_NAME)));
            String line;
            while ((line = file.readLine()) != null)
                lines.add(line);
            file.close();
        } catch (IOException ex) {}
    }

    public static void main(String[] args) {
        System.out.println(lines);
    }
}