package model;

/**
 * model.Data - working with data set
 *
 * @author Sergey Iryupin
 * @version 0.1.3 dated Apr 09, 2018
 */
import java.util.List;
import java.util.ArrayList;

import static tools.IConstants.*;

public class Data {
    Dim dim;
    Variables variables;
    List<Float> data;
    int pointer;

    public Data(Variables variables, Dim dim) {
        this.variables = variables;
        this.dim = dim;
        data = new ArrayList<>();
    }

    public boolean init(ProgramLines programLines) {
        data.clear();
        for (Integer line : programLines.keySet()) {
            String str = programLines.get(line);
            if (str.startsWith(OPER_DATA)) {
                String list = str.substring(OPER_DATA.length());
                String[] numbers = list.split(",");
                for (int i = 0; i < numbers.length; i++)
                    try {
                        data.add(Float.parseFloat(numbers[i]));
                    } catch (NumberFormatException ex) {
                        System.out.println(ERR_ILLEGAL_CONSTANT);
                        return false;
                    }
            }
        }
        pointer = 0;
        return true;
    }

    public int read(String str) {
        String[] names = str.split(",");
        for (int i = 0; i < names.length; i++)
            if (pointer < data.size())
                if (variables.isNameValid(names[i].trim()))
                    variables.put(names[i].trim(), data.get(pointer++));
                else if (dim.isItemValid(names[i]))
                    dim.put(names[i], dim.getX(names[i]), dim.getY(names[i]), data.get(pointer++));
                else {
                    System.out.println(ERR_ILLEGAL_VARIABLE);
                    return -1;
                }
            else {
                System.out.println(ERR_NO_DATA);
                return -1;
            }
        return 0;
    }
}