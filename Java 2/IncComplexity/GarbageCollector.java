/**
 * Java. Level 2. Tasks of increased complexity
 * Task:
 *  To show a garbage collector work
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Aug 30, 2017
 */
class GarbageCollector {

    public static void main(String[] args) {
        for (int i = 0; i < 580000; i++)
            new Cat();
        System.out.println("Done.");
    }
}

class Cat {
    @Override
    public void finalize() {
        System.out.println("Object will destroyed.");
    }
}