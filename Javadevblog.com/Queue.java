/*
 * Implementation of queue using array
 * Access type: FIFO (First In First Out)
 */

class Queue {
    private int[] queue;
    private int size; // maximum number of elements
    private int length; // current number of elements
    private int head;
    private int tail;
 
    public Queue(int size) {
        this.size = size;
        queue = new int[size];
        tail = -1;
        head = 0;
        length = 0;
    }

    void add(int elem) {
        if (tail == size - 1) { // cyclical movement
            tail = -1;
        }
        queue[++tail] = elem;
        length++;
    }

    int extract() {
        int temp = queue[head++]; // get first element from queue
 
        if (head == size) { // cyclical movement
            head = 0;
        }
        length--; // decrease number of elements
        return temp;
    }

    int gethead() {
        return queue[head];
    }

    int gettail() {
        return queue[tail];
    }

    boolean isFull() {
        return (length == size - 1);
    }
 
    boolean isEmpty() {
        return (length == 0);
    }
 
    int getSize() {
        return length;
    }

    public String toString() {
        String result = "[";
        int pointer = head;
        while (pointer != tail) {
            result += String.valueOf(queue[pointer]) + ((pointer != tail)? ", " : "");
            pointer++;
            if (pointer == size) pointer = 0;
        }
        return result + String.valueOf(queue[pointer]) + "]";
        //return (String.valueOf(head) + ", " + String.valueOf(tail));
   }
}