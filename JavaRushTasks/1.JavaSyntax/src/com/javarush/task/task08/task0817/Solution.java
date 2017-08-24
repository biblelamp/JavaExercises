package com.javarush.task.task08.task0817;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Нам повторы не нужны
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

    public static void removeTheFirstNameDuplicates(HashMap<String, String> map) {
        ArrayList<String> list = new ArrayList();
        for (HashMap.Entry<String, String> item : map.entrySet()) {
            list.add(item.getValue());
        }
        for (String name : list)
            if (Collections.frequency(list, name) > 1)
                removeItemFromMapByValue(map, name);
        //System.out.println(map); // чтобы увидеть результаты
    }

    public static void removeItemFromMapByValue(HashMap<String, String> map, String value) {
        HashMap<String, String> copy = new HashMap<String, String>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value))
                map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
        removeTheFirstNameDuplicates(createMap());
    }
}