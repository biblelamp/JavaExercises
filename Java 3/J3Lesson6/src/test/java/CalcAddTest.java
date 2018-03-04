import org.junit.Assert; 
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

@Ignore
public class CalcAddTest {
    private Calculator calculator;

    @Before
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd1() {
        Assert.assertEquals(0, calculator.add(0, 0));
    }

    public void testAdd2() {
        Assert.assertEquals(2, calculator.add(1, 1));
    }

    public void testAdd3() {
        Assert.assertEquals(4, calculator.add(2, 2));
    }

    public void testAdd4() {
        Assert.assertEquals(10, calculator.add(5, 5));
    }

    public void testAdd5() {
        Assert.assertEquals(6, calculator.add(4, 2));
    }

    public void testAdd6() {
        Assert.assertEquals(5, calculator.add(1, 3));
    }

    public void testAdd7() {
        Assert.assertEquals(4, calculator.add(6, -2));
    }

    public void testAdd8() {
        Assert.assertEquals(4, calculator.add(-1, 5));
    }
}