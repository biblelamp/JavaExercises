/**
 * Java. jBasic - the simplest BASIC
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 24, 2018
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

class JBasic {

    final String CMD_NEW = "new";
    final String CMD_LIST = "list";
    final String CMD_DELETE = "delete";
    final String CMD_SAVE = "save";
    final String CMD_LOAD = "load";
    final String CMD_RUN = "run";
    final String CMD_EXIT = "exit";
    final String FILE_EXT = ".bas";

    final String OPER_INPUT = "input";
    final String OPER_PRINT = "print";
    final String OPER_IF = "if";
    final String OPER_GOTO = "goto";

    Map<Integer, String> program;
    Map<Integer, String> variables;

    public static void main(String[] args) {
        new JBasic();
    }

    JBasic() {
        Scanner scr = new Scanner(System.in);
        program = new TreeMap<>();
        variables = new HashMap<>();
        String str;
        do {
            System.out.print("> ");
            str = scr.nextLine();
            String[] cmd = str.split(" ");
            switch (cmd[0]) {
                case CMD_NEW:
                    deleteAll();
                    break;
                case CMD_LIST:
                    list();
                    break;
                case CMD_DELETE:
                    delete(str);
                    break;
                case CMD_SAVE:
                    save(str);
                    break;
                case CMD_LOAD:
                    load(str);
                    break;
                case CMD_RUN:
                    run();
                    break;
                case CMD_EXIT:
                    break;
                default:
                    if (getLineNumber(str) > 0)
                        addLine(str);
            }
        } while (!str.equals(CMD_EXIT));
    }

    void deleteAll() {
        program.clear();
    }

    void delete(String str) {
        String[] part = str.split(" ");
        try {
            program.remove(Integer.parseInt(part[1]));
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
        try (PrintWriter write = new PrintWriter(part[1] + FILE_EXT)) {
            for(Map.Entry<Integer, String> line : program.entrySet())
                write.println(line.getKey() + " " + line.getValue());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    void load(String str) {
        String[] part = str.split(" ");
        try (Scanner read = new Scanner(new File(part[1] + FILE_EXT))) {
            deleteAll();
            while (read.hasNext()) {
                String line = read.nextLine();
                addLine(line);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    int getLineNumber(String str) {
        String[] part = str.split(" ");
        try {
            return Integer.parseInt(part[0]);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    void addLine(String str) {
        program.put(getLineNumber(str), str.substring(str.indexOf(" ") + 1));
    }

    void run() {
        List<Integer> lines = new ArrayList(program.keySet());
        for (int i = 0; i < lines.size(); i++)
            execute(program.get(lines.get(i)));
    }

    int execute(String str) {
        String[] cmd = str.split(" ");
        switch (cmd[0]) {
            case OPER_PRINT:
                print(str);
                break;
            case OPER_INPUT:
                input(str);
                break;
            case OPER_IF:
                ifThen(str);
                break;
            case OPER_GOTO:
                goTo(str);
                break;
            default:
                let(str);
        }
        return 0;
    }

    void print(String str) {
        for (int i = str.indexOf(" ") + 1; i < str.length(); i++)
            switch (str.charAt(i)) {
                case ';':
                case ',':
                case '"':
                    break;
                default:
                    System.out.print(str.charAt(i));
            }
        System.out.println();
    }

    void input(String str) {
    }

    void ifThen(String str) {
    }

    void goTo(String str) {
    }

    void let(String str) {
    }
}