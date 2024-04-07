package lesson34;

import java.util.Comparator;

public class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return (o1.getAge() < o2.getAge()) ? -1 : ((o1.getAge() == o2.getAge()) ? 0 : 1);
    }
}
