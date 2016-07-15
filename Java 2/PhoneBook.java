import java.util.*;

public class PhoneBook {
    private HashMap<String, PhoneRecord> pb;
	private Scanner sc = new Scanner(System.in);

	public PhoneBook() {
        pb = new HashMap<String, PhoneRecord>();
		pb.put("ivanov", new PhoneRecord("234-22-12", "ivanov@mail.com"));
		pb.put("petrov", new PhoneRecord("234-22-21", "petrov@mail.com"));
		pb.put("sidorov", new PhoneRecord("233-12-13", "sidorov@mail.com"));
		pb.put("vasilev", new PhoneRecord("232-11-23", "vasilev@mail.com"));
	}

    public void go() {
        System.out.print("Get Phone By Name: ");
        System.out.println(getPhoneByName(sc.next().toLowerCase()));

        System.out.print("Get Email By Name: ");
        System.out.println(getEmailByName(sc.next().toLowerCase()));
    }

	public String getPhoneByName(String name) {
        try {
            PhoneRecord o = pb.get(name);
            return o.getPhone();
        } catch(NullPointerException e) { 
            return "Not found.";
        }
	}

	public String getEmailByName(String name) {
        try {
            PhoneRecord o = pb.get(name);
            return o.getEmail();
        } catch(NullPointerException e) { 
            return "Not found.";
        }
	}
}