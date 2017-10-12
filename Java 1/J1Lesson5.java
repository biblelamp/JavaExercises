class J1Lesson5 {

    public static void main(String[] args) {
        Cat cat = new Cat("Murzik", "black", 1);
        cat.setColor(null);
        System.out.println("Cat " + cat.name + " color " + cat.getColor() + 
            " age " + cat.age + " say " + cat.voice());
    }
}

class Cat {
    String name;
    private String color;
    int age;

    Cat(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    void setColor(String color) {
        if (color != null)
            this.color = color;
    }

    String getColor() {
        return color;
    }

    String voice() {
        return "meow!";
    }
}