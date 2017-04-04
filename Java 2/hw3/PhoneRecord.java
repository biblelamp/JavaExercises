/**
 * Java. Level 2. Lesson 3. Example of homework
 * Class PhoneRecord
 */
package hw3;
import java.util.*;

public class PhoneRecord {
    private List<String> phones;
    private List<String> emails;

    public PhoneRecord(String phone, String email) {
        phones = new ArrayList<>();
        emails = new ArrayList<>();
        phones.add(phone);
        emails.add(email);
    }

    public void addPhone(String phone) {
        phones.add(phone);
    }

    public String getPhones() {
        return phones.toString();
    }    

    public String getEmails() {
        return emails.toString();
    }
}