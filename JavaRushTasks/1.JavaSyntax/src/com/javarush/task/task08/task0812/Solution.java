package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        int[] a = {2, 4, 4, 4, 8, 8, 9, 12, 12, 12, 12, 12, 14};

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < a.length; i++)
            //list.add(Integer.parseInt(reader.readLine()));
            list.add(a[i]);

        int sequence = 1;
        int count = 1;

        for (int i = 0; i < list.size() - 1; i++)
            if (list.get(i).equals(list.get(i + 1))) count++;
            else {
                if (count > sequence) sequence = count;
                count = 1;
            }
        System.out.println(count > sequence ? count: sequence);
    }
}