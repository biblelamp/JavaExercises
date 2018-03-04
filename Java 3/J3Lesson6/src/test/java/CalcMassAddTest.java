import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Collection;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class CalcMassAddTest {
    private Calculator calculator;
    private int a;
    private int b;
    private int c;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            {0, 0, 0},
            {1, 1, 2},
            {2, 2, 4},
            {5, 5, 10},
            {4, 2, 6},
            {1, 3, 4},
            {6, -2, 4},
            {-1, 5, 4},
        });
    }

    public CalcMassAddTest(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void massTestAdd() {
        Assert.assertEquals(c, calculator.add(a, b));
    }
}