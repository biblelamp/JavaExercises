package lesson31;

import java.lang.reflect.Field;

public class ReflectionDemo {
    public static void main(String[] args) {
        Cat cat = new Cat("Barsik", 5);
        System.out.println(cat);

        try {
            Field field = cat.getClass().getDeclaredField("a");
            field.setAccessible(true);
            field.set(cat, 8);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(cat);

        // вызов метода voice() объекта cat с помощью рефлексии
        // TODO домашнее задание
    }
}
