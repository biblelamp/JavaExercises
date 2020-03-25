package program;

import util.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class ProgramLines {

    private final static String ERROR_WRITING_FILE = "Error writing file '%s'\n";
    private final static String ERROR_READING_FILE = "Error reading file '%s'\n";
    private final static String BAD_LINE_NUMBER = "Error: Bad line number %s\n";

    private Map<Float, String> programLines;

    public ProgramLines() {
        programLines = new TreeMap<>();;
    }

    public void add(String numLine, String line) {
        programLines.put(Float.valueOf(numLine), line.substring(line.indexOf(' ') + 1));
    }

    public void write() {
        for (Float key : programLines.keySet()) {
            System.out.printf("%02d.%02d %s\n", key.intValue(), (int) (key * 100 % 100), programLines.get(key));
        }
    }

    public void erase() {
        programLines.clear();
    }

    public void input(String fileName) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                if (Util.isValidLineNumber(tokens[0])) {
                    add(tokens[0], line);
                } else {
                    System.out.printf(BAD_LINE_NUMBER, tokens[0]);
                }
            }
        } catch (IOException e) {
            System.out.printf(ERROR_READING_FILE, fileName);
        }
    }

    public void output(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(fileName), StandardCharsets.UTF_8))){
            for (Float key : programLines.keySet()) {
                writer.write(String.format("%02d.%02d %s\n",
                        key.intValue(), (int)(key * 100 % 100), programLines.get(key)));
            }
        } catch (IOException e) {
            System.out.printf(ERROR_WRITING_FILE, fileName);
        }
    }

}
