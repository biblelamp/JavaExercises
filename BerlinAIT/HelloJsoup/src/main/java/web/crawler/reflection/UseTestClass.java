package web.crawler.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UseTestClass {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> cls = Class.forName("web.crawler.reflection.TestClass");
        Constructor<?> constructor = cls.getConstructor(String.class);
        TestClass testClass = (TestClass) constructor.newInstance("test text");
        testClass.exec();
    }
}
