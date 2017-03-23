/**
 * Class Main for testing all classes of project
 * 
 * @author  Sergey Iryupin
 * @version 0.3 dated Mar 23, 2017
 */
public class Main {

    public static void main(String[] args) {
        Animal[] zoo = {new Cat("Murzik"), new Hen("Izzy"), new Hippo("Hippopo")};

        for (Animal animal : zoo) {
            System.out.println(animal);
            System.out.println(" voice: " + animal.voice());
            System.out.println((animal instanceof Swimable)?" can swim":" can not swim");
            System.out.println((animal instanceof Jumpable)?" can jump":" can not jump");
        }
    }
}