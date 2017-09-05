package com.javarush.task.task09.task0929;

import java.io.*;

/* 
Обогатим код функциональностью!
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream fileInputStream = null;

        do {
            String sourceFileName = reader.readLine();
            try {
                fileInputStream = getInputStream(sourceFileName);
            } catch (IOException ex) {
                System.out.println("Файл не существует.");
            }
        } while (fileInputStream == null);

        String destinationFileName = reader.readLine();
        OutputStream fileOutputStream = getOutputStream(destinationFileName);

        int data;
        while ((data = fileInputStream.read()) != -1)
            fileOutputStream.write(data);

        fileInputStream.close();
        fileOutputStream.close();
    }

    public static InputStream getInputStream(String fileName) throws IOException {
        return new FileInputStream(fileName);
    }

    public static OutputStream getOutputStream(String fileName) throws IOException {
        return new FileOutputStream(fileName);
    }
}

