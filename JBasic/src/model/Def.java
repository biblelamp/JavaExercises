package model;

/**
 * model.Def - working with with defined functions
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Mar 24, 2018
 */
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

    public static float calculate(String fn, float x) {

        // define name and value of parameter

        // store value of variable with same name

        // save value in the variable

        // calculate expression

        // restore value of the variable

        return 0;
    }
}