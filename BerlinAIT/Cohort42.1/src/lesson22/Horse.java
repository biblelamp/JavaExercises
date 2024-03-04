package lesson22;

public class Horse {
    private String name;

    public Horse(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println("run...");
    }

    public void walk() {
        System.out.println("walk...");
    }

    public void hidden() {
        System.out.println("Horse hidden...");
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                '}';
    }
}
