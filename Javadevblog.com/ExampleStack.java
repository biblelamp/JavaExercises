class ExampleStack {

    public static void main(String[] args) {
        Stack stack = new Stack(10);

        stack.push(79);
        stack.push(56);
        stack.push(32);
        stack.push(24);
 
        stack.pop();
 
        System.out.print("Stack: ");
        while (!stack.isEmpty()) {
            int value = stack.pop();
            System.out.print(value + " ");
        }
        System.out.println();
    }
}

class Stack {
    private int size;
    private int[] stackArray;
    private int sp;

    Stack(int size) {
        this.size = size;
        stackArray = new int[size];
        sp = -1;
    }

    void push(int element) {
        stackArray[++sp] = element;
    }

    int pop() {
        return stackArray[sp--];
    }
 
    int peek() {
        return stackArray[sp];
    }
 
    boolean isEmpty() {
        return (sp == -1);
    }
 
    boolean isFull() {
        return (sp == size - 1);
    }
}