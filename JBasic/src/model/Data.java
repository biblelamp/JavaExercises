package model;

/**
 * model.Data - working with data set
 *
 * @author Sergey Iryupin
 * @version 0.1.1 dated Apr 09, 2018
 */
import java.util.List;
import java.util.ArrayList;

import static tools.IConstants.*;

public class Data {
    Variables variables;
    List<Float> data;
    int pointer;

    public Data(Variables variables) {
        this.variables = variables;
        data = new ArrayList<>();
    }

    public void init(ProgramLines programLines) {
        for (Integer line : programLines.keySet()) {
            String str = programLines.get(line);
            if (str.startsWith(OPER_DATA)) {
                String list = str.substring(OPER_DATA.length());
                String[] numbers = list.split(",");
                for (int i = 0; i < numbers.length; i++)
                    try {
                        data.add(Float.parseFloat(numbers[i]));
                    } catch (NumberFormatException ex) {
                        data.add(0f);
                    }
            }
        }
        pointer = 0;
    }

    public int read(String str) {
        String[] names = str.split(",");
        for (int i = 0; i < names.length; i++)
            if (pointer < data.size())
                if (variables.isNameValid(names[i].trim()))
                    variables.put(names[i].trim(), data.get(pointer++));
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
