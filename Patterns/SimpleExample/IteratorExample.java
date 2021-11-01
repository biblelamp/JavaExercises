// Упрощенный интерфейс итератора

interface Iterator<E> {
    boolean hasNext();
    E next();
}

// Упрощенный интерфейс списка

interface List<E> {
    Iterator<E> iterator();
    boolean add(E e);
    int size();
}

// Класс реализующий работу со списком, а также с итератором. Является упрощённой реализацией одноименного класса из пакета java.util

class ArrayList<E> implements List<E> {
    private Object[] elementData;
    private int maxSize;
    private int size = 0;

    public ArrayList() {
        this(10);
    }

    public ArrayList(int maxSize) {
        this.maxSize = maxSize;
        elementData = new Object[maxSize];
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            return (E) elementData[cursor++];
        }
    }

    public int size() {
        return size;
    }

    public boolean add(E e) {
        if (size > maxSize) {
            return false;
        }
        elementData[size++] = e;
        return true;
    }

    @Override
    public String toString() {
        Iterator<E> it = iterator();
        if (!it.hasNext()) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        while (true) {
            sb.append(it.next());
            if (!it.hasNext()) {
                return sb.append(']').toString();
            }
            sb.append(',').append(' ');
        }
    }
}

// Проверка работы списка и итератора

public class IteratorExample {
    public static void main(String[] args) {
        List<String> abc = new ArrayList<>();
        abc.add("a");
        abc.add("b");
        abc.add("c");
        System.out.println(abc);

        Iterator<String> it = abc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}