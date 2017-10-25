package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();
    public static Properties prop = new Properties();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        InputStream input = new FileInputStream(file);
        load(input);
    }

    public void save(OutputStream outputStream) throws Exception {
        for (Map.Entry<String, String> item : properties.entrySet())
            prop.setProperty(item.getKey(), item.getValue());
        prop.store(outputStream, null);
    }

    public void load(InputStream inputStream) throws Exception {
        prop.load(inputStream);
        Enumeration<?> e = prop.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = prop.getProperty(key);
            properties.put(key, value);
        }
    }

    public static void main(String[] args) {
    }
}