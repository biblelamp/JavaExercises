package calc;

import calc.cpu.Processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Java. Calculator - test task from cngroup.dk
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Jan 8, 2019
 */

public class Calculator {

    final static String FILE_NAME = "calculations.txt";

    List<String> lines;
    Processor cpu;

    public Calculator(String fileName) {
        lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(fileName),
                    StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Calculator execute() {
        cpu = new Processor();
        for (String line : lines) {
            cpu.processLine(line);
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        List<Double> results = cpu.getResults();
        for (Double number : results) {
            if (Math.floor(number) == number) {
                sb.append(String.format("%.0f\n", number));
            } else {
                sb.append(number + "\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Calculator(FILE_NAME).execute());
    }
}
