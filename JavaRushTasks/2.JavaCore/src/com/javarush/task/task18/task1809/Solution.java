package com.javarush.task.task18.task1809;

import java.io.*;

/*
Реверс файла
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1name = reader.readLine();
        String file2name = reader.readLine();

        FileInputStream file1 = new FileInputStream(file1name);
        FileOutputStream file2 = new FileOutputStream(file2name);

        if (file1.available() > 0) {
            byte[] buffer = new byte[file1.available()];
            int count = file1.read(buffer);
            for (int i = 0; i < count / 2; i++) { // reverse array
                byte tmp = buffer[i];
                buffer[i] = buffer[count - i - 1];
                buffer[count - i - 1] = tmp;
            }
            file2.write(buffer, 0, count);
        }
        file1.close();
        file2.close();
    }
}