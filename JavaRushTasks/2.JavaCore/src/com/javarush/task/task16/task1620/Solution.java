package com.javarush.task.task16.task1620;

import java.util.ArrayList;
import java.util.List;

/* 
Один для всех, все - для одного
*/

public class Solution {
    public static byte countThreads = 3;
    static List<Thread> threads = new ArrayList<Thread>(countThreads);

    public static void main(String[] args) throws InterruptedException {
        initThreadsAndStart();
        Thread.sleep(3000);
        ourInterruptMethod();
    }

    public static void ourInterruptMethod() {
        //add your code here - добавь код тут
    }

    private static void initThreadsAndStart() {
        Water water = new Water("water");
        for (int i = 0; i < countThreads; i++) {
            threads.add(new Thread(water, "#" + i));
        }

        for (int i = 0; i < countThreads; i++) {
            threads.get(i).start();
        }
    }

    public static class Water implements Runnable {
        private String commonResource;

        public Water(String commonResource) {
            this.commonResource = commonResource;
        }

        public void run() {
            //fix 2 variables - исправь 2 переменных
            boolean isCurrentThreadInterrupted = false;
            String threadName = "";

            try {
                while (!isCurrentThreadInterrupted) {

                    System.out.println("Объект " + commonResource + ", нить " + threadName);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
            }
        }
    }
}
