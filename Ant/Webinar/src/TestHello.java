import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestHello {

    @Test
    public void testHello() {
        HelloWorld hello = new HelloWorld();
        assertEquals("Hello, World!", hello.sayHello());
    }
}