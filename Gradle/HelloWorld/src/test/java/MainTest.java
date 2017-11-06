import org.junit.Test;
import org.junit.Assert;

public class MainTest {
    @Test
    public void doTest() {
        Assert.assertEquals("Hello World!", Main.getHello());
    }
}