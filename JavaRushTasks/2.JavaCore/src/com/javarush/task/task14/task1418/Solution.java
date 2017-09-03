package com.javarush.task.task14.task1418;

import java.util.LinkedList;
import java.util.List;

/* 
Исправь четыре ошибки
*/

public class Solution {
    public static void main(String[] args) {
        List<Number> list = new LinkedList<Number>();
        //3
        list.add(new Double(1000f));
        list.add(new Double("123e-445632"));
        list.add(new Float(-90 / -3));
        list.remove(new Double("123e-445632"));

        //4 - Исправь 2 ошибки
        for (int i = 0; i <= list.size(); i--) {
            System.out.println(list.get(i));
        }

        //5
        for (Number object : list) {
            //Исправь 2 ошибки
            if (object instanceof Float) {
                Double a = (Double) object;
                System.out.println("Is float value defined? " + !(a.isNaN()));
            } else if (object instanceof Double) {
                Float a = (Float) object;
                System.out.println("Is double value infinite? " + a.isInfinite());
            }
        }
    }
}
