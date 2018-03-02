import org.junit.Assert;
import org.junit.Test;

public class CalcTest {
    private Calculator calculator;

    @Test
    public void testAdd() {
        calculator = new Calculator();
        Assert.assertEquals(4, calculator.add(2, 2));
    }

    @Test
    public void testSub() {
        calculator = new Calculator();
        Assert.assertEquals(3, calculator.sub(5, 2));
    }

    @Test
    public void testMul() {
        calculator = new Calculator();
        Assert.assertEquals(9, calculator.mul(3, 3));
        }

    @Test
    public void testDiv() {
        calculator = new Calculator();
        Assert.assertEquals(1, calculator.div(2, 2));
    }
}