package com.javarush.task.task08.task0815;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/* 
Перепись населения
*/

public class Solution {
    public static HashMap<String, String> createMap() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Иванов", "Иван");
        map.put("Петров", "Петр");
        map.put("Сидоров", "Сидор");
        map.put("Васильев", "Василий");
        map.put("Гаврилов", "Гаврила");
        map.put("Матвеев", "Иван");
        map.put("Васечкин", "Петр");
        map.put("Гаврюшин", "Гаврила");
        map.put("Кузнецов", "Иван");
        map.put("Пирогов", "Петр");
        return map;
    }

    public static int getCountTheSameFirstName(HashMap<String, String> map, String name) {
        int count = 0;
        /*for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry value = (Map.Entry) iterator.next();
            if (value.getValue().equals(name))
                count++;
        }*/
        for (String value : map.values())
            if (value.equals(name)) count++;
        return count;
    }

    public static int getCountTheSameLastName(HashMap<String, String> map, String lastName) {
        /*int count = 0;
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry value = (Map.Entry) iterator.next();
            if (value.getKey().equals(lastName))
                count++;
        }
        return count;*/
        return (map.get(lastName) != null)? 1 : 0;
    }

    public static void main(String[] args) {
        System.out.println(getCountTheSameFirstName(createMap(), "Иван"));
        System.out.println(getCountTheSameLastName(createMap(), "Иванов"));
    }
}
