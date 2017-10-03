package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> buffer = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        String line = null;
        if (args.length  > 0)
            if (args[0].equals("-c")) {

                // read all string of file in buffer
                BufferedReader fin = new BufferedReader(new FileReader(fileName));
                while (fin.ready())
                    buffer.add(fin.readLine());
                fin.close();

                // define the next id
                int max = 0;
                for (String str : buffer) {
                    int id = Integer.parseInt(str.substring(0, 8).trim());
                    if (max < id)
                        max = id;
                }
                String id = Integer.toString(max + 1);

                // write all string to the file
                BufferedWriter fout = new BufferedWriter(new FileWriter(fileName));
                for (String str : buffer)
                    fout.write(str + "\n");

                // write the last string
                fout.write(String.format("%-8s%-30s%-8s%-4s",
                        id, args[1], args[2], args[3]));

                fout.close();
            }
    }
}