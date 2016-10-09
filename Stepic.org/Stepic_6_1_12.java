class Stepic_6_1_12 {

    public static void main(String[] args) {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        System.out.println(pair.equals(pair2)); // must be true!
        System.out.println(pair.hashCode() == pair2.hashCode()); // must be true!
    }
}

/*
 * Implement Generis class Pair, like the Optional,
 * but containing a pair of elements of different types and
 * prohibits the elements have a value of null
 */

class Pair<M, N> {
    //private static final Pair<?, ?> EMPTY = new Pair<>();
    private final M valueM;
    private final N valueN;

    private Pair() {
        this.valueM = null;
        this.valueN = null;
    }

    /*public static<M,N> Pair<M,N> empty(){
        @SuppressWarnings("unchecked")
        Pair<M,N> mn = (Pair<M,N>) EMPTY;
        return mn;
    }*/

    private Pair(M valueM, N valueN) {
        this.valueM = valueM;
        this.valueN = valueN;
    }

    public static<M,N> Pair<M, N> of(M valueM1, N valueN1) {
        return new Pair<>(valueM1, valueN1);
    }

    public M getFirst() {
        return this.valueM;
    }

    public N getSecond() {
        return this.valueN;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        Pair<M, N> otherPair = (Pair<M, N>) obj;
        if (valueM != null ? !valueM.equals(otherPair.getFirst()) : otherPair.getFirst() != null) return false;
        return !(valueN != null ? !valueN.equals(otherPair.getSecond()) : otherPair.getSecond() != null);
    }
    @Override
    public int hashCode(){
        return valueM.hashCode()+valueN.hashCode();
    }
}