/**
 * Class Main for testing all classes of project
 * 
 * @author  Sergey Iryupin
 * @version 0.1 dated Mar 18, 2017
 */
public class Main {

    public static void main(String[] args) {
        Animal cat = new Cat("Murzik");
        Animal dog = new Dog("Barboz");
        Animal hen = new Hen("Izzy");
      
        System.out.println(cat);
        System.out.println(dog);
        System.out.println(hen);
    }
}