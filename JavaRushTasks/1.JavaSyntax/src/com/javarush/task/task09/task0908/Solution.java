package com.javarush.task.task09.task0908;

/* 
Исключение при работе со строками
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        try {

            String s = null;
            String m = s.toLowerCase();

        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
    }
}
