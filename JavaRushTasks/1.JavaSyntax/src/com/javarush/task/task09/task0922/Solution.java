package com.javarush.task.task09.task0922;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Какое сегодня число?
*/

public class Solution {

    public static void main(String[] args) throws Exception {
        //try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            Date date = new SimpleDateFormat("MM/dd/yyyy").parse(reader.readLine());
            SimpleDateFormat out = new SimpleDateFormat("MMM dd, YYYY", Locale.ENGLISH);

            System.out.println(out.format(date).toUpperCase());
        //} catch (ParseException e) {
        //    System.out.println("Wrong format. Try this: MM/DD/YYYY");
        //}
    }
}