/*
 * Implementation of stack using ArrayList with generic
 * Access type: LIFO (Last In First Out)
 */

import java.util.*;

class DimamicStack<T> {
    private List<T> stack = new ArrayList<T>();

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        T item = peek();
        stack.remove(stack.size() - 1);
        return item;
    }

    public T peek() {
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return (stack.size() == 0);
    }

    public String toString() {
        return stack.toString();
    }
}