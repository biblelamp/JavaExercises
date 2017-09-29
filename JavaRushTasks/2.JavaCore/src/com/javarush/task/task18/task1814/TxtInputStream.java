package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {
    FileInputStream component;

    public TxtInputStream(String fileName) throws UnsupportedFileNameException, IOException {
        super(fileName);

        if (fileName.endsWith(".txt"))
            component = new FileInputStream(fileName);
        else {
            super.close();
            throw new UnsupportedFileNameException();
        }
    }

    @Override
    public int read() throws IOException {
        return component.read();
    }

    @Override
    public int read(byte[] b) throws IOException {
        return component.read(b);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return component.read(b, off, len);
    }

    @Override
    public long skip(long n) throws IOException {
        return component.skip(n);
    }

    @Override
    public int available() throws IOException {
        return component.available();
    }

    @Override
    public void close() throws IOException {
        super.close();
        component.close();
    }

    public static void main(String[] args) {
    }
}