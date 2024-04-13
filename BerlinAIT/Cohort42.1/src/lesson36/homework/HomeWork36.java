package lesson36.homework;

/**
 * AIT-TR, cohort 42.1, Java Basic, hw #36
 *
 * @author Sergey Iryupin
 * @version 11-Apr-24
 */
public class HomeWork36 {
    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        pb.add("Luke", 12345);
        pb.add("Mark", 42135);
        pb.add("Mark", 90321);
        pb.add("Mark", 55311);

        System.out.println(pb.get("Luke"));
        System.out.println(pb.get("Mark"));
        System.out.println(pb.get("Bill"));
        System.out.println(pb);
    }
}
