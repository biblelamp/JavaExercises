package com.javarush.task.task17.task1705;

import java.util.ArrayList;
import java.util.List;

/* 
Сад-огород
*/

public class Solution {

    public static void main(String[] args) {
    }

    public static class Garden {

        public final List<String> fruits = new ArrayList<String>();
        public final List<String> vegetables = new ArrayList<String>();

        synchronized public void addFruit(int index, String fruit) {
            fruits.add(index, fruit);
        }

        synchronized public void removeFruit(int index) {
            fruits.remove(index);
        }

        synchronized public void addVegetable(int index, String vegetable) {
            vegetables.add(index, vegetable);
        }

        synchronized public void removeVegetable(int index) {
            vegetables.remove(index);
        }
    }
}