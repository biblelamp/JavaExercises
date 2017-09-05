package com.javarush.task.task06.task0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Родственные связи кошек
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Cat catGrandpa = new Cat(reader.readLine());
        Cat catGrandma = new Cat(reader.readLine());
        Cat catPa = new Cat(reader.readLine(), null, catGrandpa);
        Cat catMa = new Cat(reader.readLine(), catGrandma, null);
        Cat catSon = new Cat(reader.readLine(), catMa, catPa);
        Cat catDaughter = new Cat(reader.readLine(), catMa, catPa);

        System.out.println(catGrandpa);
        System.out.println(catGrandma);
        System.out.println(catPa);
        System.out.println(catMa);
        System.out.println(catSon);
        System.out.println(catDaughter);
    }

    public static class Cat {
        private String name;
        private Cat mother, father;

        Cat(String name) {
            this.name = name;
        }

        Cat(String name, Cat mother, Cat father) {
            this.name = name;
            this.mother = mother;
            this.father = father;
        }

        @Override
        public String toString() {
            return "Cat name is " + name +
                ((mother == null)? ", no mother" : ", mother is " + mother.name) +
                ((father == null)? ", no father" : ", father is " + father.name);
        }
    }
}