/**
 * Java. Level 3. Lesson 3. Homework
 * 1. Create two streams, each of which prints its own letter (A, B and the C), the procedure must be ABCABCABC...
 * 2. 
 */

class HomeWork5 {

    volatile char ch = 'A';

    public static void main(String[] args) {
        HomeWork5 hw = new HomeWork5();
        hw.first();
    }

    void first() {

        Thread a = new Thread(new RunnableExample('A'));
        Thread b = new Thread(new RunnableExample('B'));
        Thread c = new Thread(new RunnableExample('C'));
        a.start();
        b.start();
        c.start();
    }

    class RunnableExample implements Runnable {
        char name, counter;

        RunnableExample(char name) {
            this.name = name; // name of thread
            counter = 50;
        }

        @Override
        public void run() {
            while (counter > 0) {
                if (name == ch) {
                    System.out.print(name);
                    ch++;
                    if (ch > 'C') ch = 'A';
                    counter--;
                }
            }
        }
    }
}