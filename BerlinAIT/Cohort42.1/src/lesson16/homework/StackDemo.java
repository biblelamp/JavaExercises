package lesson16.homework;

public class StackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack();
        System.out.println(stack.toPrint());
        stack.push(12);
        stack.push(24);
        stack.push(6);
        System.out.println(stack.toPrint());
        System.out.println(stack.pop());
        System.out.println(stack.toPrint());
        System.out.println(stack.pop());
        System.out.println(stack.toPrint());
    }
}
