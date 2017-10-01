package com.javarush.task.task18.task1816;

import java.io.FileInputStream;
import java.io.IOException;

/*
Английские буквы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int countEL = 0;
        FileInputStream file = new FileInputStream(args[0]);
        while (file.available() > 0) {
            byte b = (byte) file.read();
            if ((b >= 'A' && b <= 'Z') || (b >= 'a' && b <= 'z'))
                countEL++;
        }
        file.close();
        System.out.println(countEL);
    }
}