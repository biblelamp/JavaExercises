package lesson9;

import java.util.Iterator;

public class RubberList<T> implements Iterable<T> {
    private int size;
    private Node<T> first;
    private Node<T> last;

    // queue methods

    public T poll() {
        if (first != null) {
            T value = first.value;
            first = first.next;
            first.pred = null;
            size--;
            return value;
        }
        return null;
    }

    public T peek() {
        return first == null? null : first.value;
    }

    // stack methods

    public void push(T value) {
        add(value);
    }

    public T pop() {
        T value = getLast();
        if (size == 1) {
            first = null;
        } else {
            last = last.pred;
            last.next = null;
        }
        size--;
        return value;
    }

    // common methods

    public T getFirst() {
        return first == null? null : first.value;
    }

    public T getLast() {
        return last == null? getFirst() : last.value;
    }

    public void add(T value) {
        if (first == null) { // добавляем 1й элемент
            first = new Node<>(value, null, null);
        } else if (last == null) { // добавляем 2й элемент
            last = new Node<>(value, first, null);
            first.next = last;
        } else { // добавляем 3й и все последующие
            Node<T> newLast = new Node<>(value, last, null);
            last.next = newLast;
            last = newLast;
        }
        size++;
    }

    public void addAll(T... values) {
        for (T value : values) {
            add(value);
        }
    }

    public boolean remove(T value) {
        if (first != null) {
            Node<T> cursor = first;
            do {
                if (cursor.value.equals(value)) {
                    // remove value
                    if (cursor.pred == null) { // удаляем 1й элемент
                        if (first.next != null) { // если в списке больше чем 1 элемент
                            first = first.next;
                            first.pred = null;
                        } else {
                            first = null;
                        }
                    } else if (cursor.next == null) { // удаляем последний элемент
                        last = last.pred;
                        last.next = null;
                    } else { // удаляем элемент из середины
                        cursor.pred.next = cursor.next;
                        cursor.next.pred = cursor.pred;
                    }
                    size--;
                    if (size == 1) {
                        last = null;
                    }
                    return true;
                }
                cursor = cursor.next;
            } while (cursor != null);
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        private Node<T> cursor = first;
        private T value;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            value = cursor.value;
            cursor = cursor.next;
            return value;
        }

        @Override
        public void remove() {
            if (value != null) {
                RubberList.this.remove(value);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (first != null) {
            Node<T> cursor = first;
            do {
                sb.append(cursor.value);
                cursor = cursor.next;
                if (cursor != null) {
                    sb.append(", ");
                }
            } while (cursor != null);
        }
        return sb.append("]").toString();
    }

    private class Node<T> {
        T value;
        Node<T> pred;
        Node<T> next;

        public Node(T value, Node<T> pred, Node<T> next) {
            this.value = value;
            this.pred = pred;
            this.next = next;
        }
    }
}
