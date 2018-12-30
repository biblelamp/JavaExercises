/**
 * Java. Level 1. Calculator
 *
 * @author Sergey Iryupin
 * @version 0.2 dated Dec 30, 2018
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {

    private final int HEIGHT = 300;
    private final int WIDTH = 250;
    private final Character[][] mButtonsText = {{'7', '8', '9', '/'}, {'4', '5', '6', '*'}, {'1', '2', '3', '-'}, {'0', '.', '+', 'C'}};
    private JLabel mCalculatorScreen;
    private Character mPreviousSign = null;
    private StringBuilder sb = new StringBuilder();
    private double mFirstOperand = 0;
    private double mSecondOperand = 0;
    private double mResult = 0;

    public Calculator() {
        setTitle("Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(200, 200, WIDTH, HEIGHT);
        setResizable(false);

        mCalculatorScreen = new JLabel("0", SwingConstants.RIGHT);
        mCalculatorScreen.setBorder(BorderFactory.createLoweredBevelBorder());
        mCalculatorScreen.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.ipady = 10;
        constraints.ipadx = 10;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        add(mCalculatorScreen, constraints);

        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                constraints.gridx = column;
                constraints.gridy = row + 1;
                add(createButton(mButtonsText[row][column]), constraints);
            }
        }
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(createButton('='), constraints);
        setVisible(true);
    }

    private JButton createButton(Character text) {
        JButton button = new JButton(text.toString());
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buttonPressed(text);
            }
        });
        return button;
    }

    private void buttonPressed(Character text) {
        if (Character.isDigit(text) || text.equals('.')) {
            if (text.equals('.') && mCalculatorScreen.getText().equals("0")) {
                sb.append("0.");
                mCalculatorScreen.setText(sb.toString());
            } else {
                if (sb.toString().equals("0")) sb.delete(0, sb.length());
                sb.append(text);
                mCalculatorScreen.setText(sb.toString());
            }
        } else {
            if (text.equals('C')) {
                clearCalculator("0");
                return;
            }
            if (mPreviousSign == null) {
                if (text.equals('-') && mCalculatorScreen.getText().equals("0")) {
                    sb.replace(0, sb.length(), "-");
                    mCalculatorScreen.setText(sb.toString());
                    return;
                }                
                mPreviousSign = text;
                mFirstOperand = Double.parseDouble(mCalculatorScreen.getText());
           } else {
                mSecondOperand = Double.parseDouble(sb.toString());
                switch (mPreviousSign) {
                    case '+':
                        mResult = mFirstOperand + mSecondOperand;
                        break;
                    case '-':
                        mResult = mFirstOperand - mSecondOperand;
                        break;
                    case '*':
                        mResult = mFirstOperand * mSecondOperand;
                        break;
                    case '/': {
                        if (mSecondOperand != 0) {
                            mResult = mFirstOperand / mSecondOperand;
                        } else {
                            sb.delete(0, sb.length());
                            clearCalculator("Division by zero!");
                            return;
                        }
                        break;
                    }
                }
                String[] resultArray = String.valueOf(mResult).split("\\.");
                if (Long.parseLong(resultArray[1]) > 0)
                    mCalculatorScreen.setText(String.valueOf(mResult));
                else 
                    mCalculatorScreen.setText(resultArray[0]);
                if (!text.equals('=')) {
                    mPreviousSign = text;
                    mFirstOperand = mResult;
                } else 
                    mPreviousSign = null;
            }
            sb.replace(0, sb.length(), "0");
        }
    }

    private void clearCalculator(String text) {
        sb.replace(0, sb.length(), "0");
        mCalculatorScreen.setText(text);
        mFirstOperand = 0;
        mSecondOperand = 0;
        mResult = 0;
        mPreviousSign = null;
    }

    public static void main(String[] args) {
        new Calculator();
    }
}