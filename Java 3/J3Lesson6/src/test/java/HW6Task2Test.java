import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class HW6Task2Test {
    private HW6Lesson hw;
    private boolean result;
    private int[] array;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {new int[]{1, 4, 1, 4}, true},
            {new int[]{1, 1, 1}, false},
            {new int[]{4, 4}, false},
            {new int[]{1, 4, 5}, false}
        });
    }

    public HW6Task2Test(int[] array, boolean result){
        this.array = array;
        this.result = result;
    }

    @Before
    public void init() {
        hw = new HW6Lesson();
    }

    @Test
    public void test(){
        Assert.assertEquals(result, hw.validateArrayByOneAndFour(array));
    }
}