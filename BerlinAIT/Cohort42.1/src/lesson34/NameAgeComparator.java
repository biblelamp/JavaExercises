package lesson34;

import java.util.Comparator;

public class NameAgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        int nameCompare = o1.getName().compareTo(o2.getName());
        if (nameCompare == 0) {
            return (o1.getAge() < o2.getAge()) ? -1 : ((o1.getAge() == o2.getAge()) ? 0 : 1);
        }
        return nameCompare;
    }
}
