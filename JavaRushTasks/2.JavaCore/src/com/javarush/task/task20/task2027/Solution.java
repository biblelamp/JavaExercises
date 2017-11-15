package com.javarush.task.task20.task2027;

import java.util.List;
import java.util.ArrayList;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        for (Word word : detectAllWords(crossword, "home", "same"))
            System.out.println(word);
        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        int[] endXY;
        List<Word> list = new ArrayList<>();
        for (int y = 0; y < crossword.length; y++)
            for (int x = 0; x < crossword[y].length; x++)
                for (String wd : words)
                    if ((char)crossword[y][x] == wd.charAt(0))
                        if ((endXY = getEndXY(crossword, wd, x, y)) != null) {
                            Word word = new Word(wd);
                            word.setStartPoint(x, y);
                            word.setEndPoint(endXY[0], endXY[1]);
                            list.add(word);
                        }
        return list;
    }

    private static int[] getEndXY(int[][] crossword, String word, int x, int y) {
        for (int dy = -1; dy < 2; dy++)
            for (int dx = -1; dx < 2; dx++)
                if (getWord(crossword, x, y, dx, dy, word.length()).equals(word))
                    return (new int[]
                        {x + dx*(word.length()-1), y + dy*(word.length()-1)});
        return null;
    }

    private static String getWord(int[][] crossword, int x, int y,
            int dx, int dy, int len) {
        String word = "";
        for (int i = 0; i < len; i++, x += dx, y += dy)
            word += getChar(crossword, x, y);
        return word;
    }

    private static char getChar(int[][] crossword, int x, int y) {
        if (y < 0 || x < 0 ||
            y > crossword.length - 1 || x > crossword[y].length - 1)
            return (char) 0;
        return (char)crossword[y][x];
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}