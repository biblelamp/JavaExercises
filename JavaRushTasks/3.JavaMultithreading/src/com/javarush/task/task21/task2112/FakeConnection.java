package com.javarush.task.task21.task2112;

public class FakeConnection {

    public FakeConnection() {
        System.out.println("Creating database connection...");
    }

    public void unsupportedOperation() {
        System.out.println("Operation is not supported yet!");
        throw new RuntimeException("UnsupportedOperation!");
    }

    public void usefulOperation() {
        System.out.println("Executing useful operation.");
    }
}
