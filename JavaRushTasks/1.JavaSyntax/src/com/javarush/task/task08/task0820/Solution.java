package com.javarush.task.task08.task0820;

import java.util.HashSet;
import java.util.Set;

/* 
Множество всех животных
*/

public class Solution {
    public static void main(String[] args) {
        Set<Cat> cats = createCats();
        Set<Dog> dogs = createDogs();

        Set<Object> pets = join(cats, dogs);
        printPets(pets);

        removeCats(pets, cats);
        printPets(pets);
    }

    public static Set<Cat> createCats() {
        Set<Cat> set = new HashSet<Cat>();
        for (int i = 0; i < 4; i++)
            set.add(new Cat());
        return set;
    }

    public static Set<Dog> createDogs() {
        Set<Dog> set = new HashSet<Dog>();
        for (int i = 0; i < 3; i++)
            set.add(new Dog());
        return set;
    }

    public static Set<Object> join(Set<Cat> cats, Set<Dog> dogs) {
        Set<Object> set = new HashSet<Object>();
        set.addAll(cats);
        set.addAll(dogs);
        return set;
    }

    public static void removeCats(Set<Object> pets, Set<Cat> cats) {
        pets.removeAll(cats);
    }

    public static void printPets(Set<Object> pets) {
        for (Object o : pets)
            System.out.println(o);
    }

    public static class Cat {  }

    public static class Dog {  }
}
