package model;

/**
 * model.ProgramLines - working with programLines lines
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Jan 26, 2018
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import static tools.IConstants.*;
import tools.Tools;

public class ProgramLines {
    Map<Integer, String> program;

    public ProgramLines() {
        program = new TreeMap<>();
    }

    public void list() {
        for(Integer line : program.keySet())
            System.out.println(line + " " + program.get(line));
    }

    public void save(String str) {
        String[] part = str.split(" ");
        try (PrintWriter write = new PrintWriter(
                Tools.getPartOfString(str, 1) + FILE_EXT)) {
            for(Map.Entry<Integer, String> line : program.entrySet())
                write.println(line.getKey() + " " + line.getValue());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void load(String str) {
        try (Scanner read = new Scanner(
                new File(Tools.getPartOfString(str, 1) + FILE_EXT))) {
            deleteAll();
            while (read.hasNext()) {
                String line = read.nextLine();
                add(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void add(String str) {
        if (str.split(" ").length > 1)
            program.put(Tools.getLineNumber(str), str.substring(str.indexOf(" ") + 1));
        else
            delete(str);
    }

    public void delete(String str) {
        try {
            program.remove(Integer.parseInt(str));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void deleteAll() {
        program.clear();
    }

    public String get(int key) {
        return program.get(key);
    }

    public Set<Integer> keySet() {
        return program.keySet();
    }
}