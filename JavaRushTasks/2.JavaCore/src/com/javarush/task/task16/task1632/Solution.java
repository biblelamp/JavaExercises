package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new One());
        threads.add(new Two());
        threads.add(new Three());
        threads.add(new Four());
        threads.add(new Five());
    }

    public static void main(String[] args) throws InterruptedException {
        //for (Thread th : threads)
        //    th.start();
    }

    static class One extends Thread {
        @Override
        public void run() {
            while (true) {}
        }
    }

    static class Two extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    static class Three extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Ура");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    static class Four extends Thread implements Message {
        @Override
        public void showWarning() {
            interrupt();
        }
        @Override
        public void run() {
            while (!isInterrupted()) {}
        }
    }

    static class Five extends Thread {
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int sum = 0;
            String s = null;
            while (true) {
                try {
                    s = reader.readLine();
                } catch (IOException e) {
                }
                if (s.equals("N") == true)
                    break;
                sum += Integer.parseInt(s);
            }
            System.out.println(sum);
        }
    }
}