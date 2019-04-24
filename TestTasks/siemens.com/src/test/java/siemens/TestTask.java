package siemens;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestTask {

    private SiemensTask siemensTask;

    char[][] rect = {{'1','2','3','4'}, {'5','6','7','8'}, {'9','a','b','c'}};
    char[] result = {'1','2','3','4','8','c','b','a','9','5','6','7'};

    int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
    int[][] rotated = {{3, 6, 9}, {2, 5, 8}, {1, 4, 7}};

    @Before
    public void prepareObject() {
        siemensTask = new SiemensTask();
    }

    @Test
    public void testWalkLikeSnail() {
        Assert.assertArrayEquals(result, siemensTask.walkLikeSnail(rect));
    }

    @Test
    public void testRotateMatrix() {
        Assert.assertArrayEquals(rotated, siemensTask.rotateMatrix(matrix));
    }
}