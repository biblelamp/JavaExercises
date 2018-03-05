/**
 * Java. Level 3. Lesson 7
 * Explore class using only class Class
 *
 * @see https://docs.oracle.com/javase/8/docs/api/java/lang/Class.html
 */
public class ExploreClass {

    public static void main(String[] args) {
        Class catClass = Cat.class;
        System.out.println("Full name: " + catClass.getName());
        System.out.println("Short name: " + catClass.getSimpleName());

        Class superClass = catClass.getSuperclass();
        System.out.println("Parent full name: " + superClass.getName());
        System.out.println("Parent short name: " + superClass.getSimpleName());

        for (Class face : catClass.getInterfaces())
            System.out.println("Interface: " + face.getName());

        try {
            Cat cat = (Cat)catClass.newInstance();
            System.out.println(cat.getClass().getName() + " say " + cat.voice());
        } catch (IllegalAccessException | InstantiationException ex) {
            ex.printStackTrace();
        }
    }
}

interface Voice {
    String voice();
}

interface Run {}
interface Jump {}

class Cat implements Voice, Run, Jump {
    public String name;
    public String color;
    public int age;

    @Override
    public String voice() {
        return "meow!";
    }
}