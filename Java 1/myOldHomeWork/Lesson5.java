/**
 * Java. Level 1. Lesson 5. Homework
 *	+	Create several classes: Dog, Horse, Cat - inheritance from a parent class Animal.
 *	+	All classes must be able to perform actions: run, swim, jump.
 *	+	Each class limitations to each of the action (for example, the height of the jump,
 *		the dog can jump a maximum half-meter, cat 1-2 meter; believe that cats can not swim, etc.).
 *		Every action has to output the result to the console (the animal was able to perform an action or not).
 *	+	* Often the system can exist essentially only in a single copy, for example, the system of reference
 *		of the system log messages or display driver. In such cases, you must be able to create a single instance
 *		of the class to provide access to it from the outside, and to prohibit the creation of multiple
 *		instances of the same type.
 *		That is: you must create a class (we will call it - Singleton), which
 *		- Exist in the system in a single copy
 *		- Can not create a second copy of the outside class
 *		- We always refer to the same instance of the use of this class
 *
 * @author Sergey Iryupin
 * @version 27 June 2016
 */
import animal.Animal;
import animal.Dog;
import animal.Horse;
import animal.Cat;

 public class Lesson5 {

    public static void main(String[] args) {
        // test the object Dog
        Dog d = new Dog();
        d.run(8);
        d.run(12);
        d.jump(0.5f);
        d.jump(3.8f);
        d.swim();
        // test the object Horse
        Horse h = new Horse();
        h.run(45);
        h.run(80);
        h.jump(2.2f);
        h.jump(2.8f);
        h.swim();
        // test the object Cat
        Cat c = new Cat();
        c.run(12);
        c.run(14);
        c.jump(1.7f);
        c.jump(2.9f);
        c.swim();
    }

    /**
     * Class Singleton // source: https://habrahabr.ru/post/27108/
     */
    static class Singleton {
        private static Singleton instance;
        private Singleton() {
        }
        public static Singleton getInstance() {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
}