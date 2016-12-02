class ExampleStack {

    public static void main(String[] args) {
        //Stack stack = new Stack(10);
        DimamicStack<Integer> stack = new DimamicStack<Integer>();

        stack.push(79);
        stack.push(56);
        stack.push(32);
        stack.push(24);
        stack.push(11);
 
        stack.pop();
 
        System.out.println("Stack: " + stack);
        /*while (!stack.isEmpty()) {
            int value = stack.pop();
            System.out.print(value + " ");
        }
        System.out.println();
        */
    }
}