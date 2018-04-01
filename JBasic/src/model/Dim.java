package model;

/**
 * model.Dim - working with arrays
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Apr 01, 2018
 */
import java.util.HashMap;
import java.util.Map;

import static tools.IConstants.OPER_DIM;

public class Dim {
    Variables variables;
    Map<String, String> dim;

    public Dim(Variables variables) {
        this.variables = variables;
        dim = new HashMap<>();
    }

    public void init(ProgramLines programLines) {
        for (Integer line : programLines.keySet()) {
            String str = programLines.get(line);
            if (str.startsWith(OPER_DIM)) {
                String list = str.substring(OPER_DIM.length());
                String[] items = list.split("\\)[,]?");
                for (int i = 0; i < items.length; i++)
                    dim.put(items[i].trim().substring(0, 1),
                            items[i].trim().substring(2));
            }
        }
    }
}