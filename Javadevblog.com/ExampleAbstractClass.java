class ExampleAbstractClass {

    public static void main(String args[]){
        Person student = new Employee("Andrew", "Male", 0);
        Person employee = new Employee("Susie", "Female", 1);

        System.out.println(student + " is " + student.status());
        System.out.println(employee + " is " + employee.status());
    }
}

class Employee extends Person {
    private int empId;

    Employee(String name, String gender, int id) {
        super(name, gender);
        this.empId = id;
    }

    @Override
    String status() {
        return (empId == 0) ? "unemployed" : "employee";
    }
}

abstract class Person {
    private String name;
    private String gender;

    Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    abstract String status(); // abstract method

    @Override
    public String toString(){
        return "Name: " + name + " Sex: " + gender;
    }
}