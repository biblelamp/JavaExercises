class ExampleComposition {

    public static void main(String[] args) {
        Person person = new Person();
        person.setSalary(10000L);
        System.out.println("Salary is " + person.getSalary());
    }
}

class Person {
    private Job job; // use the composition: a person has a job

    Person(){
        job = new Job();
     }

    void setSalary(long salary) {
        job.setSalary(salary);
    }

    long getSalary() {
        return job.getSalary();
    }
}

class Job {
    private String role;
    private long salary;
    private int id;

    String getRole() {
        return role;
    }

    void setRole(String role) {
        this.role = role;
    }

    long getSalary() {
        return salary;
    }

    void setSalary(long salary) {
        this.salary = salary;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    } 
}