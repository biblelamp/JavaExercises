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
    private ProgramLines programLines;

    public Def(ProgramLines programLines) {
        this.programLines = programLines;
        def = new HashMap<>();
    }

    public void init() {
        def.clear();
        for (Integer line : programLines.keySet()) {
            String str = programLines.get(line);
            if (str.startsWith(FUNC_FN)) {

            }
        }
    }

    public static float calculate(String fn, float x) {
        return 0;
    }
}
