package siemens;

import java.util.Arrays;

public class SiemensTask {

    static Character[][] rect = {{'1','2','3','4'}, {'5','6','7','8'}, {'9','a','b','c'}};
    static Integer[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};

    public static void main(String[] args) {
        SiemensTask siemensTask = new SiemensTask();
        System.out.println(Arrays.toString(siemensTask.walkLikeSnail(rect)));
        System.out.println(Arrays.toString(siemensTask.walkLikeSnail(matrix)));
        System.out.println(Arrays.deepToString(siemensTask.rotateMatrix(matrix)));
        System.out.println(Arrays.deepToString(siemensTask.rotateMatrix(matrix, true)));
    }

    public <T> T[] walkLikeSnail(T[][] rect) {
        int height = rect.length;
        int width = rect[0].length;
        T[]result = (T[])new Object[height * width];

        int row = 0;
        int col = 0;
        int dx = 1;
        int dy = 0;
        int turns = 0;
        int counter = width;

        for (int i = 0; i < width * height; i++) {
            result[i] = rect[row][col];
            counter--;
            if (counter == 0) {
                counter = width * (turns % 2) + height * ((turns + 1) % 2) - (turns/2) - 1;
                int temp = dx;
                dx = -dy;
                dy = temp;
                turns++;
            }
            col += dx;
            row += dy;
        }

        return result;
    }

    public <T> T[][] rotateMatrix(T[][] matrix) {
        return rotateMatrix(matrix, false);
    }

    public <T> T[][] rotateMatrix(T[][] matrix, boolean clockwise) {
        T[][] result = (T[][])new Object[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (clockwise) {
                    result[j][matrix.length - i - 1] = matrix[i][j];
                } else {
                    result[matrix[i].length - j - 1][i] = matrix[i][j];
                }
            }
        }
        return result;
    }
}