/**
 * Java. Level 1. Lesson 4. Homework
 *	+	Create a class Person with fields: name, position, email, telephone, salary, age;
 *	+	The class constructor must complete these fields when creating an object;
 *	+	Within the class write method, which displays information about the object in the console;
 *	+	Create an array of 5 persons and fill it;
 *	+	* With the cycle display information only for persons older than 40 years;
 * 
 * @author Sergey Iryupin
 * @version 23 June 2016
  */
public class Lesson4 {

    public static void main(String[] args) {

        Person[] arrPerson = new Person[5];
        arrPerson[0] = new Person("Ivanov Ivan", "Engineer", "iivanov@mail.com", "2312312", 30000, 30);
        arrPerson[1] = new Person("Petrov Petr", "Lead Engineer", "ppenrov@mail.com", "2365113", 42000, 39);
        arrPerson[2] = new Person("Vasilev Vasil", "Head of Department", "vvasilev@mail.com", "2365001", 55000, 55);
        arrPerson[3] = new Person("Sidorov Sidor", "Assistant", "ssidorov@mail.com", "2365223", 25000, 42);
        arrPerson[4] = new Person("Sergeev Sergey", "Trainee", "ssergeev@mail.com", "2365113", 15000, 25);

        for (int i = 0; i<arrPerson.length; i++) {
            if (arrPerson[i].getAge() > 40) {
                arrPerson[i].showPerson();
            }
        }
    }

    /**
     * Class Person
     */
    static class Person {

        private String name;
        private String position;
        private String email;
        private String phone;
        private int salary;
        private int age;

        public Person(String name, String position, String email, String phone, int salary, int age) {
            this.name = name;
            this.position = position;
            this.email = email;
            this.phone = phone;
            this.salary = salary;
            this.age = age;
        }

		public int getAge() {
			return this.age;
		}

        public void showPerson() {
            System.out.println(
                this.name +
                "\n| Position: " + this.position +
                "\n| Email: " + this.email +
                "\n| Phone: " + this.phone +
                "\n| Salary: " + this.salary +
                "\n| Age: " + this.age);
        }
    }
}
