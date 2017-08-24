package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Иванов", "Иван");
        map.put("Иванов", "Петр");
        map.put("Иванов", "Сидор");
        map.put("Петров", "Петр");
        map.put("Петров", "Сидов");
        map.put("Петров", "Иван");
        map.put("Сидоров", "Василий");
        map.put("Сидоров", "Иван");
        map.put("Сидоров", "Сидор");
        map.put("Васильев", "Василий");
        return map;
    }

    public static void printPeopleList(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}