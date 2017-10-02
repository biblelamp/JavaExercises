package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String filename = reader.readLine();
            if (filename.equals("exit"))
                break;
            new ReadThread(filename).start();
        }
    }

    public static class ReadThread extends Thread {
        String fileName;

        public ReadThread(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void run() {
            Map<Integer, Integer> hm = new HashMap<>();
            try {
                FileInputStream fr = new FileInputStream(fileName);
                while (fr.available() > 0) {
                    int b = fr.read();
                    Integer value = hm.get(b);
                    hm.put(b, ((value == null)? 1 : value + 1));
                }
                fr.close();
                Integer bt = null;
                int counter = 0;
                for (Map.Entry<Integer, Integer> item : hm.entrySet())
                    if (item.getValue() > counter) {
                        counter = item.getValue();
                        bt = item.getKey();
                    }
                resultMap.put(fileName, bt);
            } catch (IOException ex) {
            }
        }
    }
}