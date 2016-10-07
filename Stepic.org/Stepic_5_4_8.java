import java.io.*;
import java.util.*;

class Stepic_5_4_8 {

    public static void main(String[] args) {
        new Stepic_5_4_8().test();
    }

    void test() {
        final File fileName = new File("5_4_8.bin");
        byte[] fileBArray;

        // to prepare data and serialize it
        int countOfAnimals = 3;
        Animal cat = new Animal("Cat");
        Animal dog = new Animal("Dog");
        Animal horse = new Animal("Horse");
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(countOfAnimals);
            oos.writeObject(cat);
            oos.writeObject(dog);
            oos.writeObject(horse);
            oos.flush();
            oos.close();
        } catch(IOException e) { e.printStackTrace(); }
        
        // deserialize it and show
        fileBArray = new byte[(int)fileName.length()];
        try {
            FileInputStream fis = new FileInputStream((fileName));
            fis.read(fileBArray);
        } catch(IOException e) { e.printStackTrace(); }

        Animal[] animals = deserializeAnimalArray(fileBArray);
        for (Animal animal : animals)
            System.out.println(animal.getName());
    }

    /*
     * Method that restores an array of objects Animal from the transmitted byte array
     */

    public static Animal[] deserializeAnimalArray(byte[] data) throws IllegalArgumentException {
        Animal[] animals = null;
        int len;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(data));
            len = ois.readInt();
            animals = new Animal[len];
            for (int i = 0; i < animals.length; i++) {
                animals[i] = (Animal) ois.readObject();
            }
            ois.close();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return animals;
    }
}

class Animal implements Serializable {
    private final String name;

    public Animal(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Animal) {
            return Objects.equals(name, ((Animal) obj).name);
        }
        return false;
    }
}