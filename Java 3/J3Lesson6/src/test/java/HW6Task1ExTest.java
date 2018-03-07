import org.junit.Test;

public class HW6Task1ExTest {

    @Test(expected = RuntimeException.class)
    public void exTest() {
        new HW6Lesson().getNumberAfrerLastFour(new int[]{2, 3});
    }
}