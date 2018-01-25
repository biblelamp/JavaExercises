/**
 * ProgramLines - working with programLines lines
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 25, 2018
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class ProgramLines implements IConstants {
    Map<Integer, String> program;

    ProgramLines() {
        program = new TreeMap<>();
    }

    void deleteAll() {
        program.clear();
    }

    void delete(String str) {
        try {
            program.remove(Integer.parseInt(Tools.getPieceOfString(str, 1)));
        } catch (NumberFormatException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void list() {
        for(Map.Entry<Integer, String> line : program.entrySet())
            System.out.println(line.getKey() + " " + line.getValue());
    }

    void save(String str) {
        String[] part = str.split(" ");
        try (PrintWriter write = new PrintWriter(
                Tools.getPieceOfString(str, 1) + FILE_EXT)) {
            for(Map.Entry<Integer, String> line : program.entrySet())
                write.println(line.getKey() + " " + line.getValue());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void load(String str) {
        try (Scanner read = new Scanner(
                new File(Tools.getPieceOfString(str, 1) + FILE_EXT))) {
            deleteAll();
            while (read.hasNext()) {
                String line = read.nextLine();
                add(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void add(String str) {
        program.put(Tools.getLineNumber(str), str.substring(str.indexOf(" ") + 1));
    }

    String get(int key) {
        return program.get(key);
    }

    Set<Integer> keySet() {
        return program.keySet();
    }
}