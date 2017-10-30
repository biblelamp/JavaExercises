package com.javarush.task.task20.task2013;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

/* 
Externalizable Person
*/
public class Solution {

    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person() { }

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeChars(firstName);
            out.writeChars(lastName);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeInt(age);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = in.readLine();
            lastName = in.readLine();
            mother = (Person)in.readObject();
            father = (Person)in.readObject();
            age = in.readInt();
            children = (List)in.readObject();
        }
    }

    public static void main(String[] args) {
    }
}