package lesson21.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #21
 *
 * @author Sergey Iryupin
 * @version 4-mar-24
 */
public class RubberArrayTest {

    private RubberArray rubberArray;

    @BeforeEach
    public void init() {
        rubberArray = new RubberArray();
    }

    @Test
    public void testGet() {
        rubberArray.add(12);
        Assertions.assertEquals(12, rubberArray.get(0));
    }

    @Test
    public void testAdd() {
        Assertions.assertEquals(0,rubberArray.size());
        rubberArray.add(25);
        Assertions.assertEquals(1, rubberArray.size());
        Assertions.assertEquals(25, rubberArray.get(0));
    }

    @Test
    public void testAddByIndex() {
        rubberArray.add(25);
        rubberArray.add(25);
        rubberArray.add(7, 1);
        Assertions.assertEquals(3, rubberArray.size());
        Assertions.assertEquals(7, rubberArray.get(1));
    }

    @Test
    public void testRemove() {
        rubberArray.add(-1);
        rubberArray.add(3);
        Assertions.assertEquals(2, rubberArray.size());
        rubberArray.remove(1);
        Assertions.assertEquals(1, rubberArray.size());
    }

    @Test
    public void testContains() {
        rubberArray.add(-1);
        rubberArray.add(3);
        Assertions.assertTrue(rubberArray.contains(-1));
        Assertions.assertFalse(rubberArray.contains(5));
    }

    @Test
    public void testIndexOf() {
        rubberArray.add(-1);
        rubberArray.add(3);
        Assertions.assertEquals(1, rubberArray.indexOf(3));
        Assertions.assertEquals(-1, rubberArray.indexOf(5));
    }

    @Test
    public void testToString() {
        rubberArray.add(-1);
        rubberArray.add(3);
        Assertions.assertEquals("[-1, 3]", rubberArray.toString());
    }
}
