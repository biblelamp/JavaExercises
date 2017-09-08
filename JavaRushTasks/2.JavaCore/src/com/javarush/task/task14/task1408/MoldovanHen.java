package com.javarush.task.task14.task1408;

/**
 * Created by Sergej Irupin on 08.09.17.
 */
public class MoldovanHen extends Hen {
    @Override
    int getCountOfEggsPerMonth() {
        return 8;
    }
    @Override
    String getDescription() {
        return super.getDescription() +
                " Моя страна - " + Country.MOLDOVA + ". Я несу " + getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}