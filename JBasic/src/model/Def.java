package model;

/**
 * model.Def - working with with defined functions
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Mar 24, 2018
 */
import tools.Calculate;
import tools.Tools;

import java.util.HashMap;
import java.util.Map;

import static tools.IConstants.*;

public class Def {
    Variables variables;
    private Map<String, String> def;

    public Def(Variables variables) {
        this.variables = variables;
    }

    public void init(ProgramLines programLines) {
        def = new HashMap<>();
        for (Integer line : programLines.keySet()) {
            String str = programLines.get(line);
            if (str.startsWith(OPER_DEF))
                def.put(str.substring(str.indexOf(FUNC_FN),
                        str.indexOf(FUNC_FN) + 3),
                        str.substring(str.indexOf(FUNC_FN)));
        }
    }

    public float calculate(String fn, float x) {

        // define name of parameter and expression
        String function = def.get(fn);
        if (function == null) {
            System.out.println(ERR_UNDEFINED_FUNCTION);
            return 0;
        }
        String name = function.substring(4, 5);
        String expression = Tools.getPartOfString(function, 1, "=").trim();

        // store value of variable with same name
        float save = variables.get(name);

        // save value in the variable
        variables.put(name, x);

        // calculate expression
        float result = new Calculate(variables, this)
                .calculatePostfix(
                        Calculate.convertInfixToPostfix(expression));

        // restore value of the variable
        variables.put(name, save);

        return result;
    }
}