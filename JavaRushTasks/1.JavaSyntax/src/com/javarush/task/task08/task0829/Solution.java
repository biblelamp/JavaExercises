package com.javarush.task.task08.task0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Модернизация ПО
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //list of addresses
        //List<String> addresses = new ArrayList<String>();
        Map<String, String> addresses = new HashMap<String, String>();
        while (true) {
            String city = reader.readLine();
            if (city.isEmpty()) break;
            String family = reader.readLine();
            addresses.put(city, family);
        }

        //read home number
        //int houseNumber = Integer.parseInt(reader.readLine());

        String city = reader.readLine();

        //if (0 <= houseNumber && houseNumber < addresses.size()) {
        //    String familySecondName = addresses.get(houseNumber);
        //    System.out.println(familySecondName);
        //}
        System.out.println(addresses.get(city));
    }
}