import java.util.List;
import java.util.ArrayList;

interface Graphic {
    void print();
}

class Composite implements Graphic {
    private List<Graphic> mChildGraphics = new ArrayList<Graphic>();

    public void print() {
        for (Graphic graphic : mChildGraphics) {
            graphic.print();
        }
    }

    public void add(Graphic graphic) {
        mChildGraphics.add(graphic);
    }

    public void remove(Graphic graphic) {
        mChildGraphics.remove(graphic);
    }
}

class Leaf implements Graphic {
    public void print() {
        System.out.println("Leaf");
    }
}

public class CompositeExample {
    public static void main(String[] args) {
        Leaf leaf1 = new Leaf();
        Leaf leaf2 = new Leaf();
        Leaf leaf3 = new Leaf();
        Leaf leaf4 = new Leaf();

        Composite composite = new Composite();
        Composite composite1 = new Composite();
        Composite composite2 = new Composite();

        composite1.add(leaf1);
        composite1.add(leaf2);
        composite1.add(leaf3);

        composite2.add(leaf4);

        composite.add(composite1);
        composite.add(composite2);
        composite.print();
    }
}