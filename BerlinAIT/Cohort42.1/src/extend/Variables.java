package extend;

public class Variables {
    private int[] values = new int[26];

    public Integer getValue(String varName) {
        // check variable name
        if (!validateVarName(varName)) {
            return null;
        }
        // transform 'a' -> 0
        int idx = varName.charAt(0) - 'a';
        // return value
        return values[idx];
    }

    public void setValue(String varName, String varValue) {
        // check variable name
        if (!validateVarName(varName)) {
            return;
        }
        // transform 'a' -> 0
        int idx = varName.charAt(0) - 'a';
        // transform "123" -> 123
        int value;
        try {
            value = Integer.valueOf(varValue);
        } catch (NumberFormatException e) {
            System.out.println("Error: invalid value");
            return;
        }
        // assign value
        values[idx] = value;
    }

    private boolean validateVarName(String varName) {
        // check variable name length
        if (varName.length() > 1) {
            System.out.println("Error: variable name is too long");
            return false;
        }
        if (varName.isEmpty()) {
            System.out.println("Error: variable name is empty");
            return false;
        }
        // check if variable name is in 'a'..'z'
        if (varName.charAt(0) < 'a' || varName.charAt(0) > 'z') {
            System.out.println("Error: variable name is invalid");
            return false;
        }
        return true;
    }
}
