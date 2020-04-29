package calculations;

import java.util.Arrays;
import java.util.List;

public class Function {

    private final static List<String> functions = Arrays.asList(
            "FABS", "FATN", "FCOS", "FEXP", "FITR", "FLOG", "FRAN", "FSGN", "FSIN", "FSQT");

    /**
     * Checking if the function name is valid
     * @param funcName function name
     * @return true if function name is valid
     */
    public static boolean isFunction(String funcName) {
        return functions.contains(funcName.toUpperCase());
    }

    /**
     * Calculation of built-in functions
     * @param funcName function name
     * @param parameter
     * @return function calculation result
     */
    public static Float calculate(String funcName, Float parameter) {
        int index = functions.indexOf(funcName.toUpperCase());
        switch (index) {
            case 0: // FABS
                return Math.abs(parameter);
            case 1: // FATN
                return (float)Math.atan(parameter);
            case 2: // FCOS
                return (float)Math.cos(parameter);
            case 3: // FEXP
                return (float)Math.exp(parameter);
            case 4: // FITR
                return (float)parameter.intValue();
            case 5: // FLOG
                return (float)Math.log(parameter);
            case 6: // FRAN
                return (float)Math.random();
            case 7: // FSGN
                return Math.signum(parameter);
            case 8: // FSIN
                return (float)Math.sin(parameter);
            case 9: // FSQT
                return (float)Math.sqrt(parameter);
        }
        return null;
    }

}
