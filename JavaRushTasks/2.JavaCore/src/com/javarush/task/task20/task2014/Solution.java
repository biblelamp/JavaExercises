package com.javarush.task.task20.task2014;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/

public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File your_file_name = File.createTempFile("your_file_name", null);

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(your_file_name));
        Solution savedObject = new Solution(4);
        System.out.println(savedObject);
        oos.writeObject(savedObject);
        oos.close();

        Solution loadedObject = new Solution(10);
        System.out.println(loadedObject);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(your_file_name));
        loadedObject = (Solution) ois.readObject();

        if (savedObject.string.equals(loadedObject.string))
            System.out.println("Strings are equal");
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}