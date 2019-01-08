package calc;

import calc.cpu.Processor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestCalc {

    private Processor cpu;

    @Before
    public void startTest() {
        cpu = new Processor();
    }

    @Test
    public void testAdd() {
        cpu.processLine("add 2");
        cpu.processLine("apply 2");
        Assert.assertEquals(4, cpu.getRegister(), 0);
    }

    @Test
    public void testSubtract() {
        cpu.processLine("subtract 4");
        cpu.processLine("apply 2");
        Assert.assertEquals(-2, cpu.getRegister(), 0);
    }

    @Test
    public void testMultiply() {
        cpu.processLine("multiply 2");
        cpu.processLine("apply 2");
        Assert.assertEquals(4, cpu.getRegister(), 0);
    }

    @Test
    public void testDivide() {
        cpu.processLine("divide 2");
        cpu.processLine("apply 4");
        Assert.assertEquals(2, cpu.getRegister(), 0);
    }
}