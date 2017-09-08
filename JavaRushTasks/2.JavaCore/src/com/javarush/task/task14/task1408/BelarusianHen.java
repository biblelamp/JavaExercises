package com.javarush.task.task14.task1408;

/**
 * Created by Sergej Irupin on 08.09.17.
 */
public class BelarusianHen extends Hen {
    @Override
    int getCountOfEggsPerMonth() {
        return 10;
    }
    @Override
    String getDescription() {
        return super.getDescription() +
                " Моя страна - " + Country.BELARUS + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}