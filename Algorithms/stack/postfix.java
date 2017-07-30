/**
 * Book: Data Structures and Algorithms in Java, by Robert LaFore
 * Chapter 4:
 *  postfix.java
 *  parses postfix arithmetic expressions
 *  to compile this code: javac postfix.java
 *  to run this program:  java PostfixApp
 * Examples for testing:
 *  5+7             -> 57+          -> 12
 *  2+3*4           -> 234*+        -> 14
 *  3*(4+5)-6/(1+2) -> 345+*612+/-  -> 25
 */
import java.io.*; // for I/O

class StackX {
    private int maxSize;
    private int[] stackArray;
    private int top;

    public StackX(int s) {      // constructor
        maxSize = s;
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int j) { // put item on top of stack
        stackArray[++top] = j;
    }

    public int pop() {        // take item from top of stack
        return stackArray[top--];
    }

    public int peek() {       // peek at top of stack
        return stackArray[top];
    }

    public boolean isEmpty() { // true if stack is empty
        return (top == -1);
    }

    public int size() {        // return size
        return top+1;
    }

    public int peekN(int n) { // return item at index n
        return stackArray[n];
    }

    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        for (int j=0; j<size(); j++) {
            System.out.print(peekN(j));
            System.out.print(' ');
        }
        System.out.println("");
    }
} // end class StackX

class ParsePost {
    private StackX theStack;
    private String input;

    public ParsePost(String s) {
        input = s;
    }

   public int doParse() {
        theStack = new StackX(20);             // make new stack
        int j, num1, num2, interAns;
        char ch;

        for (j=0; j<input.length(); j++) {     // for each char,
            ch = input.charAt(j);              // read from input
            theStack.displayStack(""+ch+" ");  // *diagnostic*
            if (ch >= '0' && ch <= '9')        // if it's a number
                theStack.push((int)(ch-'0'));  //   push it
            else {                             // it's an operator
                num2 = theStack.pop();         // pop operands
                num1 = theStack.pop();
                switch(ch) {                   // do arithmetic
                    case '+':
                        interAns = num1 + num2;
                        break;
                    case '-':
                        interAns = num1 - num2;
                        break;
                    case '*':
                        interAns = num1 * num2;
                        break;
                    case '/':
                        interAns = num1 / num2;
                        break;
                    default:
                        interAns = 0;
                }
                theStack.push(interAns);        // push result
            }
        }
        interAns = theStack.pop();              // get answer
        return interAns;
    }
} // end class ParsePost

class PostfixApp {
    public static void main(String[] args) throws IOException {
        String input;
        int output;
        while(true) {
            System.out.print("Enter postfix: ");
            System.out.flush();
            input = getString();         // read a string from kbd
            if (input.equals(""))        // quit if [Enter]
                break;
            // make a parser
            ParsePost aParser = new ParsePost(input);
            output = aParser.doParse();  // do the evaluation
            System.out.println("Evaluates to " + output);
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }
}