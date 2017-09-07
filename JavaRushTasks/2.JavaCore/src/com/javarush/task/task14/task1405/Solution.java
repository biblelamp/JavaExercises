package com.javarush.task.task14.task1405;

/* 
Food
*/

public class Solution {
    public static void main(String[] args) {
        Food food = new Food();
        Selectable selectable = new Food();
        Food newFood = (Food) selectable;

        foodMethods(food);
        selectableMethods(selectable);
    }

    public static void foodMethods(Food food) {
        food.onSelect();
        food.eat();
    }

    public static void selectableMethods(Selectable selectable) {
        selectable.onSelect();
    }

    interface Selectable {
        void onSelect();
    }

    static class Food implements Selectable {
        @Override
        public void onSelect() {
            System.out.println("food was selected");
        }

        public void eat() {
            System.out.println("food was eaten");
        }
    }
}