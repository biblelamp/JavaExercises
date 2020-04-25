package program;

import interpreter.Interpreter;
import util.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ProgramLines {

    private final static String LINE_FORMAT = "%05.2f %s\n";

    private final static String EXT_FILE = ".focal";

    private Map<Float, String> programLines;

    public ProgramLines() {
        programLines = new TreeMap<>();;
    }

    public void add(String numLine, String line) {
        programLines.put(Float.valueOf(numLine), line.substring(line.indexOf(' ') + 1));
    }

    public void write() {
        for (Float key : programLines.keySet()) {
            System.out.printf(Locale.ROOT, LINE_FORMAT, key, programLines.get(key));
        }
    }

    public void write(String numLine) {
        if (Util.isValidLineNumber(numLine)) {
            float key = Float.parseFloat(numLine);
            String line = programLines.get(key);
            if (line != null) {
                System.out.printf(Locale.ROOT, LINE_FORMAT, key, line);
            }
        } else if (Util.isValidGroupNumber(numLine)) {
            Float group = Float.parseFloat(numLine);
            for (Float key : programLines.keySet()) {
                if (key.intValue() == group.intValue()) {
                    System.out.printf(Locale.ROOT, LINE_FORMAT, key, programLines.get(key));
                }
            }
        }
    }

    public String get(Float key) {
        return key == null? null : programLines.getOrDefault(key, null);
    }

    public Set<Float> keySet() {
        return programLines.keySet();
    }

    public int size() {
        return programLines.size();
    }

    public void erase() {
        programLines.clear();
    }

    public void erase(String numLine) {
        if (Util.isValidLineNumber(numLine)) {
            programLines.remove(Float.parseFloat(numLine));
        } else if (Util.isValidGroupNumber(numLine)) {
            // TODO implement line group erasing
        }
    }

    public void call(String fileName) {
        fileName = addExtensionToFileName(fileName, EXT_FILE);
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName), StandardCharsets.UTF_8))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    String[] tokens = line.split(" ");
                    if (Util.isValidLineNumber(tokens[0])) {
                        add(tokens[0], line);
                    } else {
                        System.out.printf(Interpreter.BAD_LINE_NUMBER, tokens[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.printf(Interpreter.ERROR_READING_FILE, fileName);
        }
    }

    public void save(String fileName) {
        fileName = addExtensionToFileName(fileName, EXT_FILE);
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(fileName), StandardCharsets.UTF_8))){
            for (Float key : programLines.keySet()) {
                writer.write(String.format(Locale.ROOT, LINE_FORMAT, key, programLines.get(key)));
            }
        } catch (IOException e) {
            System.out.printf(Interpreter.ERROR_WRITING_FILE, fileName);
        }
    }

    private String addExtensionToFileName(String fileName, String ext) {
        if (fileName.indexOf('.') < 0) {
            return fileName + ext;
        }
        return fileName;
    }

}
