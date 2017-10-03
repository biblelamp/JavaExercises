package com.javarush.task.task18.task1828;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
Прайсы 2
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> buffer = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        String line = null;
        if (args.length  > 0) {

            // read all file in buffer
            BufferedReader fin = new BufferedReader(new FileReader(fileName));
            while (fin.ready())
                buffer.add(fin.readLine());
            fin.close();

            // write all string to the file
            BufferedWriter fout = new BufferedWriter(new FileWriter(fileName));
            for (String str : buffer)
                if (args[0].equals("-u") && str.startsWith(args[1]))      // update
                    fout.write(String.format("%-8s%-30s%-8s%-4s\n",
                            args[1], args[2], args[3], args[4]));
                else if (args[0].equals("-d") && str.startsWith(args[1])) // delete
                    continue;
                else
                    fout.write(str + "\n");

            fout.close();
        }
    }
}