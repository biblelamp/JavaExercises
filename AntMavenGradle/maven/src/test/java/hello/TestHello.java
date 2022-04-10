package hello;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHello {

    @Test
    public void testHello() {
        HelloWorld hello = new HelloWorld();
        Assertions.assertEquals("Hello, World!", hello.sayHello());
    }
}