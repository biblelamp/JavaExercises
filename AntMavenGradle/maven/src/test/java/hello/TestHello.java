package hello;

import org.junit.Assert;
import org.junit.Test;

public class TestHello {

    @Test
    public void testHello() {
        HelloWorld hello = new HelloWorld();
        Assert.assertEquals("Hello, World!", hello.sayHello());
    }
}