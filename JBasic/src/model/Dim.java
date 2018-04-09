package model;

/**
 * model.Dim - working with arrays
 *
 * @author Sergey Iryupin
 * @version 0.2.3 dated Apr 07, 2018
 */
import java.util.HashMap;
import java.util.Map;

import static tools.IConstants.OPER_DIM;

public class Dim {
    Variables variables;
    Map<String, float[][]> dim;

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
                for (int i = 0; i < items.length; i++) {
                    String name = items[i].trim().substring(0, 1);
                    String[] idx = items[i].trim().substring(2).split(",");
                    float[][] array;
                    int idx1 = Integer.parseInt(idx[0]);
                    if (idx.length > 1) {
                        int idx2 = Integer.parseInt(idx[1]);
                        array = new float[idx2][idx1];
                    } else
                        array = new float[1][idx1];
                    dim.put(name, array);
                }
            }
        }
    }

    public boolean isItemValid(String name) {
        return false;
    }

    public int getX(String name) {
        return 0;
    }

    public int getY(String name) {
        return 0;
    }

    public float get(String name, int idX, int idY) {
        float[][] array = dim.get(name);
        return array[idY][idX];
    }

    public void put(String name, int idX, int idY, float value) {
        float[][] array = dim.get(name);
        array[idY][idX] = value;
        dim.put(name, array);
    }
}