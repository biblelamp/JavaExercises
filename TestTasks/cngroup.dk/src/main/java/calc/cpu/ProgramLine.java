package calc.cpu;

public class ProgramLine {

    private Operations operation;
    private double number;

    public ProgramLine(Operations operation, double number) {
        this.operation = operation;
        this.number = number;
    }

    public Operations getOperation() {
        return operation;
    }

    public double getNumber() {
        return number;
    }
}
