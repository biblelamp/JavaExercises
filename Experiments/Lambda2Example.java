class Lambda2Example {
    public static void main(String[] args) {
        Cat cat = new Cat();
        System.out.println(cat);

        // creating lambda
        Setdata s = (obj, name, age) -> {
            obj.setName(name);
            obj.setAge(age);
        };

        s.set(cat, "Murzik", 3);
        System.out.println(cat);
    }
}

interface Setdata {
    void set(Cat cat, String name, int age);
}

class Cat {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{name='" + name + "'" + ", age=" + age + "}";
    }
}