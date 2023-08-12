package lesson7;

import java.util.LinkedList;

/**
 * Algorithms. Lesson #7. Stack and Queue
 *
 * @author Sergey Iryupin
 * @version 11 Aug 2023
 */

public class Lesson7 {
    public static void main(String[] args) {
        LinkedList<Integer> stackQueue = new LinkedList<>();
        // LinkedList like Stack
        stackQueue.push(5);
        System.out.println(stackQueue);
        stackQueue.push(12);
        System.out.println(stackQueue);
        stackQueue.push(-2);
        System.out.println("like Stack: " + stackQueue);
        for (int i = 0; i < 3; i++) {
            System.out.println(stackQueue.pop() + " <- " + stackQueue);
        }
        // LinkedList like Queue
        stackQueue.offer(8);
        System.out.println(stackQueue);
        stackQueue.offer(-5);
        System.out.println(stackQueue);
        stackQueue.offer(11);
        System.out.println("like Queue: " + stackQueue);
        for (int i = 0; i < 3; i++) {
            System.out.println(stackQueue.poll() + " <- " + stackQueue);
        }
    }
}
