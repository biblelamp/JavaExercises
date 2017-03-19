/**
 * Class Main for testing all classes of project
 * 
 * @author  Sergey Iryupin
 * @version 0.2 dated Mar 19, 2017
 */
public class Main {

    public static void main(String[] args) {
        Animal cat = new Cat("Murzik");
        Animal dog = new Dog("Barboz");
        Animal hen = new Hen("Izzy");
        Animal elephant = new Elephant("Goliath");
      
        System.out.println(cat);
        System.out.println(dog);
        System.out.println(hen);
        System.out.println(elephant);
    }
}