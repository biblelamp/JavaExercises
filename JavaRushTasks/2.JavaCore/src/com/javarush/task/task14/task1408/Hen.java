package com.javarush.task.task14.task1408;

/**
 * Created by Sergej Irupin on 08.09.17.
 */
public abstract class Hen {
    abstract int getCountOfEggsPerMonth();
    String getDescription() {
        return "Я - курица.";
    }
}