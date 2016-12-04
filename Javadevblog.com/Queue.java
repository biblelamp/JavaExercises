/*
 * Implementation of queue using array
 * Access type: FIFO (First In First Out)
 */

class Queue {
    private int[] queue;
    private int length; // current number of elements
    private int head; // pointer to head
    private int tail; // pointer to tail

    public Queue(int size) {
        queue = new int[size];
        tail = -1;
        head = 0;
        length = 0;
    }

    boolean add(int elem) {
        if (isFull()) { // queue/array is full
            return false;
        }
        if (tail == queue.length - 1) { // cyclical movement
            tail = -1;
        }
        queue[++tail] = elem;
        length++;
        return true;
    }

    int extract() {
        int temp = queue[head++]; // get first element from queue
        if (head == queue.length) { // cyclical movement
            head = 0;
        }
        length--; // decrease number of elements
        return temp;
    }

    int getHead() {
        return queue[head];
    }

    int getTail() {
        return queue[tail];
    }

    int getSize() {
        return length;
    }

    boolean isFull() {
        return (queue.length == length);
    }

    boolean isEmpty() {
        return (length == 0);
    }

    public String toString() {
        String result = "[";
        int pointer = head;
        while (pointer != tail) {
            result += String.valueOf(queue[pointer]) + ((pointer != tail)? ", " : "");
            pointer++;
            if (pointer == queue.length) pointer = 0;
        }
        return result + String.valueOf(queue[pointer]) + "]";
   }
}