package com.javarush.task.task08.task0816;

import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static HashMap<String, Date> createMap() {
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", new Date("JUNE 1 1980"));
        map.put("Ivanov", new Date("MAY 12 1988"));
        map.put("Petrov", new Date("MARCH 8 1968"));
        map.put("Sidorov", new Date("JUNE 15 1955"));
        map.put("Vasiljev", new Date("MAY 3 1911"));
        map.put("Nikiforov", new Date("AUGUST 11 1923"));
        map.put("Pirogov", new Date("APRIL 21 1934"));
        map.put("Orlov", new Date("JULY 5 1955"));
        map.put("Zuzin", new Date("DECEMBER 12 1998"));
        map.put("Kuzkin", new Date("OCTOBER 6 1993"));
        return map;
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        /*ArrayList<String> removelist = new ArrayList<>();
        for (Map.Entry <String, Date>  pair : map.entrySet()) {
            Date date = pair.getValue();
            int m = date.getMonth();
            if (m > 4 && m < 8) // counting month from 0
                removelist.add(pair.getKey());
        }
        for (String key : removelist)
            map.remove(key);
        */
        Iterator iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, Date> pair = (Map.Entry) iterator.next();
            Date date = pair.getValue();
            if (date.getMonth() < 8 && date.getMonth() > 4)
                iterator.remove();
        }
    }

    public static void main(String[] args) {
        HashMap<String, Date> map = createMap();
        removeAllSummerPeople(map);
        System.out.println(map);
    }
}
