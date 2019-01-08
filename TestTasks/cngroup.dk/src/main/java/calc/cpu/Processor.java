package calc.cpu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Processor {

    private LinkedList<ProgramLine> lines;
    private List<Double> results;
    private double register;

    public Processor() {
        lines = new LinkedList<>();
        results = new ArrayList<>();
    }

    public List<Double> getResults() {
        return results;
    }

    public double getRegister() {
        return register;
    }

    public void processLine(String line) {
        String[] fields = line.split(" ");
        if (fields.length > 1) {
            try {
                Operations operation = Operations.valueOf(fields[0]);
                double number = Double.parseDouble(fields[1]);

                if (operation == Operations.apply) {
                    execute(number);
                } else {
                    lines.add(new ProgramLine(operation, number));
                }
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void execute(double number) {
        register = number;
        while (lines.size() > 0) {
            register = calculate(register, lines.getFirst());
            lines.remove(0);
        }
        results.add(register);
    }

    private double calculate(double register, ProgramLine line) {
        switch (line.getOperation()) {
            case add:
                register += line.getNumber();
                break;
            case subtract:
                register -= line.getNumber();
                break;
            case multiply:
                register *= line.getNumber();
                break;
            case divide:
                register /= line.getNumber();
                break;
        }
        return register;
    }
}
