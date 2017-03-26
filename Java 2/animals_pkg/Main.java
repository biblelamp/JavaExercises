import animals.*;
import obstances.*;
/**
 * Class Main for testing all classes of project
 * 
 * @author  Sergey Iryupin
 * @version 0.4 dated Mar 24, 2017
 */
public class Main {

    public static void main(String[] args) {
        Animal[] zoo = {new Cat("Murzik"), new Hen("Izzy"), new Hippo("Hippopo")};
        Track track = new Track(50);
        Water water = new Water(50);
        Wall wall = new Wall(2);

        for (Animal animal : zoo) {
            System.out.println(animal);
            System.out.println(" voice: " + animal.voice());
            System.out.println(" run: " + track.doIt(animal));
            System.out.println(" swim: " + water.doIt(animal));
            System.out.println(" jump: " + wall.doIt(animal));
        }
    }
}