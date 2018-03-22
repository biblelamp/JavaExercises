package model;

/**
 * model.Def - working with with defined functions
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Mar 22, 2018
 */
import java.util.HashMap;
import java.util.Map;

import static tools.IConstants.*;

public class Def {
    private Map<String, String> def;

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
        return 0;
    }
}
