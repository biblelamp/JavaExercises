package com.javarush.task.task18.task1826;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
Шифровка
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(args[1]);
        FileOutputStream fout = new FileOutputStream(args[2]);

        while (fin.available() > 0) {
            int b = fin.read();
            if (args[0].equals("-e"))  // encryption
                b = b + 1;
            if (args[0].equals("-d"))  // decryption
                b = b - 1;
            fout.write(b);
        }

        fin.close();
        fout.close();
    }
}