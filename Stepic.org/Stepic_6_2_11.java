import java.util.*;

class Stepic_6_2_11<T> {

    /*
     * What operations are allowed on the collection?
     */

    public static void main(String[] args) {
        Collection<?> collection = new ArrayList();
        Object object = new Object();
        //collection.addAll(Arrays.asList(object));
        collection.size();
        //collection.add(object);
        collection.toArray();
        collection.clear();
        collection.iterator();
        collection.remove(object);
        collection.contains(object);
    }
}