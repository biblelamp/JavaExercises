package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(args[0]));
        while (file.ready()) {
            String[] fields = file.readLine().split(" ");
            // make birthday
            int day = Integer.parseInt(fields[fields.length - 3]);
            int month = Integer.parseInt(fields[fields.length - 2]);
            int year = Integer.parseInt(fields[fields.length - 1]);
            Date date = new GregorianCalendar(year, month - 1, day).getTime();
            // join name
            String name = "";
            for (int i = 0; i < fields.length - 3; i++)
                name = name + fields[i] + " ";

            PEOPLE.add(new Person(name.trim(), date));
        }
        file.close();
    }
}