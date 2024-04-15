package lesson35;

public class HashTable<K, V> {
    private int capacity = 16;
    private Entry<K, V>[] table;
    private int size = 0;

    public HashTable() {
        this.table = new Entry[capacity];
    }

    public HashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
    }

    public Entry<K, V>[] getTable() {
        return table;
    }

    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        int hash = key.hashCode();
        System.out.println("hash: " + hash);
        int idx = hash & (capacity - 1);
        System.out.println("idx: " + idx);
        // bits operations:
        // 11100100111011011
        // 10011011000101011 & // 2 -> 10
        //              1111
        //              1011
        if (table[idx] == null) {
            table[idx] = entry;
        } else {
            Entry<K, V> pointer = table[idx];
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = entry;
        }
        size++;
    }

    public V get(K key) {
        int hash = key.hashCode();
        int idx = hash & (capacity - 1);
        if (table[idx] == null) {
            return null;
        } else {
            Entry<K, V> pointer = table[idx];
            do {
                if (pointer.key.equals(key)) {
                    return pointer.value;
                }
                pointer = pointer.next;
            } while (pointer != null);
        }
        return null;
    }

    public V remove(K key) {
        int hash = key.hashCode();
        int idx = hash & (capacity - 1);
        if (table[idx] == null) {
            return null;
        }
        Entry<K, V> predPointer = null;
        Entry<K, V> pointer = table[idx];
        do {
            if (pointer.key.equals(key)) {
                V value = pointer.value;
                // remove entry
                if (predPointer == null) {
                    table[idx] = pointer.next;
                } else {
                    predPointer.next = pointer.next;
                }
                size--;
                return value;
            }
            predPointer = pointer;
            pointer = pointer.next;
        } while (pointer != null);
        return null;
    }

    @Override
    public String toString() {
        int counter = 0;
        StringBuilder sb = new StringBuilder("{");
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                Entry<K, V> pointer = entry;
                do {
                    counter++;
                    sb.append(pointer.key + "=" + pointer.value);
                    if (counter < size) {
                        sb.append(", ");
                    }
                    pointer = pointer.next;
                } while (pointer != null);
            }
        }
        return sb.append("}").toString();
    }

    class Entry<K, V> {
        K key;
        V value;
        Entry next;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
