package com.javarush.task.task14.task1408;

/* 
Куриная фабрика
*/

public class Solution {
    public static void main(String[] args) {
        Hen hen = HenFactory.getHen(Country.BELARUS);
        hen.getCountOfEggsPerMonth();
        //System.out.println(hen.getDescription());
    }

    static class HenFactory {

        static Hen getHen(String country) {
            Hen hen = null;
            switch (country) {
                case "Ukraine": hen = new UkrainianHen();
                    break;
                case "Russia": hen = new RussianHen();
                    break;
                case "Moldova": hen = new MoldovanHen();
                    break;
                case "Belarus": hen = new BelarusianHen();
                    break;
            }
            return hen;
        }
    }
}