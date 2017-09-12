package com.javarush.task.task15.task1529;

/**
 * Created by Sergej Irupin on 12.09.17.
 */
public class Plane implements Flyable {

    public Plane(int passengers) {
        this.passengers = passengers;
    }

    private int passengers;

    @Override
    public void fly() {
    }
}