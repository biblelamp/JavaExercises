package com.javarush.task.task16.task1618;

/* 
Снова interrupt
*/

public class Solution {
    public static void main(String[] args) throws InterruptedException {
        TestThread thread = new TestThread();
        thread.start();
        Thread.sleep(1);
        thread.interrupt();
    }

    public static class TestThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.print(".");
                if (isInterrupted())
                    break;
            }
        }
    }
}