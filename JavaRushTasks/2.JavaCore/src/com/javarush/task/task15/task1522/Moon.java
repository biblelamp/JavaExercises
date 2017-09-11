package com.javarush.task.task15.task1522;

/**
 * Created by Sergej Irupin on 11.09.17.
 */
public class Moon implements Planet {
    private static Moon instance;

    private Moon() {}

    public static Moon getInstance() {
        if(instance == null){
            instance = new Moon();
        }
        return instance;
    }
}