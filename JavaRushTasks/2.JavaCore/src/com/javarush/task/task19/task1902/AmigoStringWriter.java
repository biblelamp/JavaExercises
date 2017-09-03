package com.javarush.task.task19.task1902;

import java.io.IOException;

public interface AmigoStringWriter {
    void flush() throws IOException;
    void writeString(String s) throws IOException;
    void close() throws IOException;
}
