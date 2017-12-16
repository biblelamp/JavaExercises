/**
 * Java. Making a table of hw results
 *
 * @author Sergey Iryupin
 * @version 0.2.1 dated Dec 16, 2017
 */
import java.io.*;
import java.util.*;

class MakeTable {

    final int ROWS = 50;
    final int COLUMNS = 9;
    final String CHECKED_FOLDER =
    "C:\\Users\\lamp\\YandexDisk\\Учебники\\Java\\ДЗ\\11 Группа";
    //"C:\\Documents and Settings\\SIrupin.DRF\\YandexDisk\\Учебники\\Java\\ДЗ\\11 Группа";
    String[][] table = new String[50][COLUMNS];

    public static void main(String[] args) {
        new MakeTable();
    }

    MakeTable() {
        // readig data from files with results
        File folder = new File(CHECKED_FOLDER);
        File[] files = folder.listFiles();
        for (File file : files)
            if (file.isFile())
                readFile(file);

        // sort
        sort();

        // print result
        System.out.println(toString());
    }

    void readFile(File file) {
        int num = file.getName().charAt(11) - 48;
        try (Scanner read = new Scanner(file)) {
            while (read.hasNext())
                addRecord(read.nextLine(), num);
        } catch (IOException ex) {}
    }

    void addRecord(String str, int num) {
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

    void sort() {
        for (int i = 0; i < ROWS; i++) {
            if (table[i][0] == null)
                break;
            for (int j = i + 1; j < ROWS; j++) {
                if (table[j][0] == null)
                    break;
                if (countPoints(i) < countPoints(j)) {
                    String[] temp = table[i];
                    table[i] = table[j];
                    table[j] = temp;
                }
            }
        }
    }

    float countPoints(int row) {
        float result = 0;
        for (int i = 1; i < table[row].length; i++)
            if (table[row][i] != null)
                switch (table[row][i]) {
                    case "":
                    case "null":
                        break;
                    case "weak":
                    case "overdue": result += 3;
                        break;
                    case "medium":
                    case "middle": result += 4;
                        break;
                    case "good": result += 5;
                        break;
                    case "good+": result += 5.5;
                        break;
                    case "excellent": result += 6;
                        break;
                    default: System.out.println("Unknown mark '" +
                        table[row][i] + "' in " + row + ":" + i);
                }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < table.length; i++) {
            if (table[i][0] == null)
                break;
            result += Arrays.toString(table[i]) + " " + countPoints(i) + "\n";
        }
        return result;
    }
}