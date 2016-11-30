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