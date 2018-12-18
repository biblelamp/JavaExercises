/**
 * Java. Level 1. Calculator
 *
 * @author Sergey Iryupin
 * @version 0.1 dated Dec 18, 2018
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

        //создаем установщик размеров и позиции объектов для компоновщика
        GridBagConstraints constraints = new GridBagConstraints();
        //задаем параметры для экрана отображения
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        //эти параметры будут неизменны для всех кнопок
        constraints.ipady = 10;
        constraints.ipadx = 10;
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;

        //добавляем экран отображения
        add(mCalculatorScreen, constraints);

        //устанавливаем параметры для кнопок
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        //в цикле добавим все кнопки на экран
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                constraints.gridx = column;
                constraints.gridy = row + 1;
                add(createButton(mButtonsText[row][column]), constraints);
            }
        }
        //отдельно внизу выведем большую кнопку "равно"
        constraints.gridwidth = 4;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(createButton('='), constraints);
        setVisible(true);
    }

    private JButton createButton(Character text) {
        //создаем кнопку с указанным текстом
        JButton button = new JButton(text.toString());
        //добавляем листенер
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                buttonPressed(text);
            }
        });
        return button;
    }
    //основная логика программы здесь - обработчик нажатия клавиш
    private void buttonPressed(Character text) {
        //если нажата цифра или точка, то добавляем знак к строителю строк
        if (Character.isDigit(text) || text.equals('.')) {
            //если на экране ноль и пользователь нажал точку, значит он хочет дробное число с целой частью 0
            if (text.equals('.') && mCalculatorScreen.getText().equals("0")) {
                sb.append("0.");
                mCalculatorScreen.setText(sb.toString());
            }
            else {
                //это условие нужно, чтобы при нажатии на ноль он не выводился несколько раз на экран, а также чтобы убрать ноль перед другой цифрой
                if (sb.toString().equals("0")) sb.delete(0, sb.length());
                sb.append(text);
                mCalculatorScreen.setText(sb.toString());
            }
        }
        else {
            //Если нажата "С", то сбрасываем калькулятор в 0
            if (text.equals('C')) {
                clearCalculator("0");
                return;
            }
            //если ранее не было нажато клавиш с операторами, то определяем первый операнд
            if (mPreviousSign == null) {
                //если на экране ноль и пользователь нажал минус, значит он хочет отрицательное число, выводим минус на экран
                if (text.equals('-') && mCalculatorScreen.getText().equals("0")) {
                    sb.replace(0, sb.length(), "-");
                    mCalculatorScreen.setText(sb.toString());
                    return;
                }                
                mPreviousSign = text;
                //первый операнд берем с экрана, чтобы поддерживалась функция "сцепки" операторов
                //т.е. чтобы можно было сделать так: 25 + 45 - 35 * 2 / 8 и т.д.
                mFirstOperand = Double.parseDouble(mCalculatorScreen.getText());
            }
            //иначе надо производить вычисления
            else {
                //определяем второй операнд
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
                        //деление на ноль запрещено
                        if (mSecondOperand != 0) {
                            mResult = mFirstOperand / mSecondOperand;
                        }
                        else {
                            sb.delete(0, sb.length());
                            clearCalculator("Division by zero!");
                            return;
                        }
                        break;
                    }
                }
                //разобьем результат на целую и дробную часть.
                String[] resultArray = String.valueOf(mResult).split("\\.");
                //если дробная часть не нулевая, то. 
                //ЗДЕСЬ БЫЛА ПРОБЛЕМА. ИСПОЛЬЗОВАЛ INTEGER, ПОЭТОМУ ПРОИСХОДИЛО ПЕРЕПОЛНЕНИЕ ПРИ ДЕЛЕНИИ НА ДЕВЯТЬ. 
                //ОСТАТОК ОТ ДЕЛЕНИЯ БЫЛ ОЧЕНЬ БОЛЬШОЙ. ПРИШЛОСЬ ИСПОЛЬЗОВАТЬ LONG
                if (Long.parseLong(resultArray[1]) > 0)
                //выводим результат на экран с дробной частью
                mCalculatorScreen.setText(String.valueOf(mResult));
                //иначе выводим только целую часть на экран
                else mCalculatorScreen.setText(resultArray[0]);
                //если была нажата клавиша равно, то сбрасываем предыдущий знак в null
                if (!text.equals('=')) {
                    mPreviousSign = text;
                    mFirstOperand = mResult;
                }
                else mPreviousSign = null;
            }
            //очищаем строителя и помещаем в него 0
            sb.replace(0, sb.length(), "0");
        }
    }
    //метод сброса калькулятора в начальное положение
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