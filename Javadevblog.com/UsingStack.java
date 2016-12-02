/*
 * Using a stack to check the brackets in expressions
 */

class UsingStack {

    public static void main(String[] args) {
        String[] str_tests = {"a[b {c} d] e", "a {b (c] d} e", "{a [b {c} d] e}"};
        for (String str : str_tests)
            System.out.println(str + " - " + (new CheckExpressions(str).check()));
    }
}

class CheckExpressions {

    private String str;
    //private Stack stack;
    DimamicStack<Character> stack;

    public CheckExpressions(String str) {
        this.str = str;
        //stack = new Stack(str.length());
        stack = new DimamicStack<Character>();
    }

    boolean check() {
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) {
                case '{':
                case '[':
                case '(':
                    stack.push(ch);
                    break;
                case '}':
                case ']':
                case ')':
                    if (!stack.isEmpty()) {
                        //char chClosed = (char) stack.pop();
                        char chClosed = stack.pop();
                        if ((ch == '}' && chClosed != '{')
                            || (ch == ']' && chClosed != '[')
                            || (ch == ')' && chClosed != '('))
                        return false;
                    } else 
                        return false;
                    break;
            }
        }
        if (!stack.isEmpty())
            return false;
        return true;
    }
}