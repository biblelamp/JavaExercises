import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class CounterApp extends JFrame {

    private int value;

    public static void main(String[] args) {
        new CounterApp(1);
    }

    public CounterApp(int initialValue) {
        setTitle("Simple Counter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 120);
        setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.BOLD, 42);
 
        JLabel counterValueView = new JLabel();
        counterValueView.setFont(font);
        counterValueView.setHorizontalAlignment(SwingConstants.CENTER);
        add(counterValueView, BorderLayout.CENTER);

        value = initialValue;
        counterValueView.setText(String.valueOf(value));

        JButton decrementButton = new JButton("<");
        decrementButton.setFont(font);
        add(decrementButton, BorderLayout.WEST);

        JButton incrementButton = new JButton(">");
        incrementButton.setFont(font);
        add(incrementButton, BorderLayout.EAST);

        decrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                value--;
                counterValueView.setText(String.valueOf(value));
            }
        });

        incrementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                value++;
                counterValueView.setText(String.valueOf(value));
            }
        });

        setVisible(true);
   }
}