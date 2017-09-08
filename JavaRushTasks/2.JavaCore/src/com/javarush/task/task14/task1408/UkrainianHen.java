package com.javarush.task.task14.task1408;

/**
 * Created by Sergej Irupin on 08.09.17.
 */
public class UkrainianHen extends Hen {
    @Override
    int getCountOfEggsPerMonth() {
        return 12;
    }
    @Override
    String getDescription() {
        return super.getDescription() +
                " Моя страна - " + Country.UKRAINE + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}