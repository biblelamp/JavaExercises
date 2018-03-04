import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

public class CalcTest {
    private Calculator calculator;

    @Before
    public void startTest() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(4, calculator.add(2, 2));
    }

    @Test
    public void testSub() {
        Assert.assertEquals(3, calculator.sub(5, 2));
    }

    @Test
    public void testMul() {
        Assert.assertEquals(9, calculator.mul(3, 3));
    }

    @Test
    @Ignore("Деление пока не тестируем")
    public void testDiv() {
        Assert.assertEquals(1, calculator.div(2, 2));
    }
}