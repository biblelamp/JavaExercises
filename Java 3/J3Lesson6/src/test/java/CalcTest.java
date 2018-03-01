import org.junit.Assert;
import org.junit.Test;

public class CalcTest {
    private Calculator calculator;
    
    @Test
    public void testAdd() {
        calculator = new Calculator();
        Assert.assertEquals(4, calculator.add(2, 2));
    }
}