package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("Иванов", 550);
        map.put("Петров", 500);
        map.put("Сидоров", 510);
        map.put("Васильев", 520);
        map.put("Гаврилов", 560);
        map.put("Матвеев", 1000);
        map.put("Васечкин", 860);
        map.put("Гаврюшин", 312);
        map.put("Кузнецов", 450);
        map.put("Пирогов", 1250);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry) iterator.next();
            if (pair.getValue() < 500)
                iterator.remove();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = createMap();
        removeItemFromMap(map);
        System.out.println(map);
    }
}