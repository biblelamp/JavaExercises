/**
 * Java. Level 2. Lesson 3. Example of homework
 * Class PhoneBook
 */
package hw3;
import java.util.*;

public class PhoneBook {
    private Map<String, PhoneRecord> pb;

    public PhoneBook() {
        pb = new TreeMap<>();
    }

    public void addRecord(String name, PhoneRecord record) {
        pb.put(name, record);
    }

    public void addPhone(String name, String phone) {
        PhoneRecord record = pb.get(name);
        if (record != null) record.addPhone(phone);
    }

    public String getPhonesByName(String name) {
        PhoneRecord record = pb.get(name);
        return (record == null)? "Not found." : record.getPhones();
    }

    public String getEmailsByName(String name) {
        PhoneRecord record = pb.get(name);
        return (record == null)? "Not found." : record.getEmails();
    }
}