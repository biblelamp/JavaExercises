package com.javarush.task.task09.task0927;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        HashMap<String, Cat> map = new HashMap<String, Cat>();
        map.put("Tima", new Cat("Tima"));
        map.put("Anti", new Cat("Anti"));
        map.put("Dimi", new Cat("Dimi"));
        map.put("Timie", new Cat("Timie"));
        map.put("Lo", new Cat("Lo"));
        map.put("Andi", new Cat("Andi"));
        map.put("Lahi", new Cat("Lahi"));
        map.put("Domh", new Cat("Domh"));
        map.put("More", new Cat("More"));
        map.put("Alea", new Cat("Alea"));
        return map;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        return new HashSet(map.values());
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }
}
