package com.javarush.task.task18.task1825;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/*
Собираем файл
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        Map<Integer, String> tm = new TreeMap<Integer, String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String resultFile = null;

        while (true) {
            String filename = reader.readLine();
            if (filename.equals("end"))
                break;
            resultFile = filename.substring(0, filename.indexOf(".part"));
            int index = Integer.parseInt(
                    filename.substring(filename.indexOf(".part") + 5));
            tm.put(index, filename);
        }

        FileOutputStream out = new FileOutputStream(resultFile);
        for (Map.Entry<Integer, String> item : tm.entrySet()) {
            FileInputStream in = new FileInputStream(item.getValue());
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            out.write(buffer);
            in.close();
        }
        out.close();
    }
}