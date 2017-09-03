package com.javarush.task.task20.task2008;

import java.io.Serializable;

/* 
Как сериализовать Singleton?
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton instance = Singleton.getInstance();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //Serializing the singleton instance
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(instance);
        oos.close();

        //Recreating the instance by reading the serialized object data add
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        Singleton singleton = (Singleton) ois.readObject();
        ois.close();

        //Recreating the instance AGAIN by reading the serialized object data add
        byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ObjectInputStream ois2 = new ObjectInputStream(byteArrayInputStream);
        Singleton singleton1 = (Singleton) ois2.readObject();
        ois2.close();

        //The singleton behavior has been broken
        System.out.println("Instance reference check : " + singleton.getInstance());
        System.out.println("Instance reference check : " + singleton1.getInstance());
        System.out.println("=========================================================");
        System.out.println("Object reference check : " + singleton);
        System.out.println("Object reference check : " + singleton1);
    }

    public static class Singleton implements Serializable {
        private static Singleton ourInstance;

        public static Singleton getInstance() {
            if (ourInstance == null) {
                ourInstance = new Singleton();
            }
            return ourInstance;
        }

        private Singleton() {
        }
    }
}
