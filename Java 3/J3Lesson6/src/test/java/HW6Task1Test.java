import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HW6Task1Test {
    private HW6Lesson hw;
    private int[] array;
    private int[] result;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new int[] {1, 2, 4, 4, 3, 4, 1, 7}, new int[]{1, 7}},
            {new int[] {1, 2, 4, 4, 2}, new int[]{2}},
            {new int[] {1, 2, 4}, new int[]{}},
        });
    }

    public HW6Task1Test(int[] array, int[] result) {
        this.array = array;
        this.result = result;
    }

    @Before
    public void init() {
        hw = new HW6Lesson();
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(result, hw.getNumberAfrerLastFour(array));
    }
}