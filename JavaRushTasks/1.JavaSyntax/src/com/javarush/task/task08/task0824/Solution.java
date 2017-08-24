package com.javarush.task.task08.task0824;

/* 
Собираем семейство
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Human c1 = new Human("Никита", true, 22, null);
        Human c2 = new Human("Артем", true, 10, null);
        Human c3 = new Human("Катя", true, 5, null);
        ArrayList<Human> children = new ArrayList<>(Arrays.asList(c1, c2, c3));
        Human f = new Human("Сергей", true, 55, children);
        Human m = new Human("Галина", false, 42, children);
        Human d1 = new Human("Владимир", true, 78, Arrays.asList(f));
        Human d2 = new Human("Александр", true, 72, Arrays.asList(m));
        Human b1 = new Human("Вера", false, 74, Arrays.asList(f));
        Human b2 = new Human("Антонина", false, 68, Arrays.asList(m));
        System.out.println(d1);
        System.out.println(b1);
        System.out.println(d2);
        System.out.println(b2);
        System.out.println(f);
        System.out.println(m);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c3);
    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        List<Human> children = new ArrayList<Human>();

        Human(String name, boolean sex, int age, List<Human> children) {
            this.name = name;
            this.sex = sex;
            this.age = age;
            if (children != null)
                this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }

}
