package com.javarush.task.task20.task2017;

import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/* 
Десериализация
*/
public class Solution {
    public A getOriginalObject(ObjectInputStream objectStream) {
        try {
            return (A) objectStream.readObject();
        } catch (NotSerializableException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) {
    }
}