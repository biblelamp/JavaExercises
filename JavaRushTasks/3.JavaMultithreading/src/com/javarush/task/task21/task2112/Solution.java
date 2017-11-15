package com.javarush.task.task21.task2112;

public class Solution {
    public static void main(String[] args) {
        DBConnectionManager dbConnectionManager = new DBConnectionManager();
        try (FakeConnection fakeConnection = dbConnectionManager.getFakeConnection()) {
            System.out.println("Entering the body of try block.");
        } catch (Exception e) {
        }
    }
}
