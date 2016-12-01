/*
 * Implementation of stack using ArrayList
 * LIFO - Last In First Out
 */

import java.util.*;

class DimamicStack {
    private List<Integer> stack = new ArrayList<>();

    public void push(int item) {
        stack.add(item);
    }

    public int pop() {
        int item = peek();
        stack.remove(stack.size() - 1);
        return item;
    }

    public int peek() {
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return (stack.size() == 0);
    }

    public String toString() {
        return stack.toString();
    }
}