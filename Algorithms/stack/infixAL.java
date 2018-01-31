/**
 * Book: Data Structures and Algorithms in Java, by Robert LaFore
 * Chapter 4:
 *  infixAL.java
 *  converts infix arithmetic expressions to postfix using LinkedList
 *  to compile this code: javac infixAL.java
 *  to run this program:  java InfixApp
 * Examples for testing:
 *  A+B*C       -> ABC*+
 *  (A+B)*C     -> AB+C*
 *  A+B*(C-D)   -> ABCD-*+
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

class StackX {
    private LinkedList<Character> stack;

    public StackX() {
        stack = new LinkedList<>();
    }

    public void push(Character ch) {
        stack.push(ch);
    }

    public char pop() {
        return stack.pop();
    }

    public char peek() {
        return stack.peek();
    }

    public int size() {
        return stack.size();
    }

    public void displayStack(String s) {
        System.out.print(s);
        System.out.print("Stack (bottom-->top): ");
        System.out.println(stack);
    }
}

class InToPost {                 // infix to postfix conversion
    private StackX theStack;
    private String input;
    private String output = "";

    public InToPost(String in) { // constructor
        input = in;
        theStack = new StackX();
    }

    public String doTrans() {    // do translation to postfix
        for (int j=0; j<input.length(); j++) { // for each char
            char ch = input.charAt(j);            // get it
            theStack.displayStack("For "+ch+" "); // *diagnostic*
            switch (ch) {
                case '+':                // it's + or -
                case '-':
                    gotOper(ch, 1);      // go pop operators
                    break;               //   (precedence 1)
                case '*':                // it's * or /
                case '/':
                    gotOper(ch, 2);      // go pop operators
                    break;               //   (precedence 2)
                case '(':                // it's a left paren
                    theStack.push(ch);   // push it
                    break;
                case ')':                // it's a right paren
                    gotParen();          // go pop operators
                    break;
                default:                 // must be an operand
                    output = output + ch;// write it to output
                    break;
            }
        }
        while (theStack.size() > 0) {    // pop remaining opers
            theStack.displayStack("While ");  // *diagnostic*
            output = output + theStack.pop(); // write to output
        }
        theStack.displayStack("End   "); // *diagnostic*
        return output;                   // return postfix
    }

    public void gotOper(char opThis, int prec1) { // got operator from input
        while (theStack.size() > 0) {
            char opTop = theStack.pop();
            if (opTop == '(') {          // if it's a '('
                theStack.push(opTop);    // restore '('
                break;
            } else {                     // it's an operator
                int prec2 = (opTop == '+' || opTop == '-')? 1 : 2;
                if (prec2 < prec1) {     // if prec of new op less
                                         //    than prec of old
                    theStack.push(opTop);// save newly-popped op
                    break;
                } else                   // prec of new not less
                    output = output + opTop; // than prec of old
            }
        }
        theStack.push(opThis);           // push new operator
    }

    public void gotParen() {             // got right paren from input
        while (theStack.size() > 0) {
            char chx = theStack.pop();
            if (chx == '(')              // if popped '('
                break;                   // we're done
            else                         // if popped operator
                output = output + chx;   // output it
        }
    }
}

class InfixApp {
    public static void main(String[] args) throws IOException {
        String input, output;
        while(true) {
            System.out.print("Enter infix: ");
            System.out.flush();
            input = getString();         // read a string from kbd
            if (input.equals(""))        // quit if [Enter]
                break;
            // make a translator
            InToPost theTrans = new InToPost(input);
            output = theTrans.doTrans(); // do the translation
            System.out.println("Postfix is " + output + '\n');
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }
}