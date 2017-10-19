package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = reader.readLine();
        String fileName2 = reader.readLine();
        reader.close();

        //String fileName1 = "file1.txt"; // for debugging
        //String fileName2 = "file2.txt";

        List<String> fs1 = new ArrayList<String>();
        List<String> fs2 = new ArrayList<String>();

        BufferedReader file1 = new BufferedReader(new FileReader(fileName1));
        while (file1.ready())
            fs1.add(file1.readLine());
        file1.close();

        BufferedReader file2 = new BufferedReader(new FileReader(fileName2));
        while (file2.ready())
            fs2.add(file2.readLine());
        file2.close();

        int j = 0;
        int i;
        for (i = 0; i < fs1.size(); i++) {
            if (fs1.get(i).equals(fs2.get(j))) {
                lines.add(new LineItem(Type.SAME, fs1.get(i)));
                if (j + 1 < fs2.size())
                    j++;
            } else {
                 String s2 = null;
                 if (j + 1 < fs2.size())
                     s2 = fs2.get(++j);
                 if (fs1.get(i).equals(s2)) {
                     lines.add(new LineItem(Type.ADDED, fs2.get(j - 1)));
                     i--;
                 } else {
                     lines.add(new LineItem(Type.REMOVED, fs1.get(i)));
                     if (s2 != null)
                        j--;
                 }
            }
        }
        if (i == fs1.size() && j < fs2.size()) // ckeck the last added string
            lines.add(new LineItem(Type.ADDED, fs2.get(j)));

        // output for debugging
        //for (LineItem line : lines)
        //    System.out.println(line.type + " " + line.line);
    }

    public static enum Type {
        ADDED,        // added new string
        REMOVED,      // deleted string
        SAME          // without changes
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}