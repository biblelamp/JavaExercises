/**
 * Java. Making a table of hw results
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Nov 30, 2017
 */
import java.io.*;
import java.util.*;

class MakeTable {

    static final int ROWS = 50;
    static final int COLUMNS = 9;
    static final String CHECKED_FOLDER =
    "C:\\Documents and Settings\\SIrupin.DRF\\YandexDisk\\Учебники\\Java\\ДЗ\\11 Группа";
    static String[][] table = new String[50][COLUMNS];

    public static void main(String[] args) {
        File folder = new File(CHECKED_FOLDER);
        File[] files = folder.listFiles();
        for (File file : files)
            if (file.isFile())
                readFile(file);

        for (int i = 0; i < table.length; i++) {
            if (table[i][0] == null)
                break;
            System.out.println(Arrays.toString(table[i]));
        }
    }

    static void readFile(File file) {
        int num = file.getName().charAt(11) - 48;
        try (Scanner read = new Scanner(file)) {
            while (read.hasNext())
                addRecord(read.nextLine(), num);
        } catch (IOException ex) {}
    }

    static void addRecord(String str, int num) {
        String[] pair = str.split("-");
        for (int i = 0; i < table.length; i++) {
            if (table[i][0] == null) {
                table[i][0] = pair[0].trim();
                table[i][num] = pair[1].trim();
                break;
            } else if (table[i][0].equals(pair[0].trim())) {
                table[i][num] = pair[1].trim();
                break;
            }
        }
    }
}