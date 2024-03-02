package lesson21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void init() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd() {
        Assertions.assertEquals(4, calculator.add(2, 2));
    }

    @Test
    public void testSub() {
        Assertions.assertEquals(4, calculator.sub(8, 4));
    }

    @Test
    public void testMul() {
        Assertions.assertEquals(9, calculator.mul(3, 3));
    }

    @Test
    public void testDiv() {
        Assertions.assertEquals(2.5, calculator.div(5, 2));
    }
}
